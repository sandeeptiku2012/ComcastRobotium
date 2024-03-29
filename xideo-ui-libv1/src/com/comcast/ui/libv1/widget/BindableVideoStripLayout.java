package com.comcast.ui.libv1.widget;

import gueei.binding.AttributeBinder;
import gueei.binding.Binder;
import gueei.binding.BindingLog;
import gueei.binding.CollectionChangedEventArg;
import gueei.binding.CollectionObserver;
import gueei.binding.ConstantObservable;
import gueei.binding.IBindableView;
import gueei.binding.IObservable;
import gueei.binding.IObservableCollection;
import gueei.binding.ISyntaxResolver.SyntaxResolveException;
import gueei.binding.InnerFieldObservable;
import gueei.binding.Observer;
import gueei.binding.collections.ObservableCollection;
import gueei.binding.utility.ObservableMultiplexer;
import gueei.binding.utility.WeakList;
import gueei.binding.viewAttributes.templates.Layout;
import gueei.binding.viewAttributes.templates.LayoutItem;
import gueei.binding.ViewAttribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RemoteViews.RemoteView;
import android.widget.TextView;
import android.view.View.*;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

@RemoteView
public class BindableVideoStripLayout extends ViewGroup implements
		IBindableView<BindableVideoStripLayout>, OnFocusChangeListener {

	protected int mSelectedIndex = 0;
	protected int mPreviousSelectedIndex = 0;
	protected int mOffsetLeft;
	protected int mItemCount;
	ConcurrentLinkedQueue<Point> pendingList = new ConcurrentLinkedQueue<Point>();

	static final int ANIMATION_DURATION_SLIDE_LEFT_RIGHT = 350; // milliseconds
	static final int ANIMATION_DURATION_SLIDE_UPDOWN = 200; // milliseconds
	static final int ANIMATION_DURATION_FOCUS_CHANGE = 200; // milliseconds

	public static final String DEFAULT_FONT_NAME = "fonts/Flama-Basic.otf";

	public IFocusHopperListener listener;

	public interface OnSelectionChangeListener {
		void onSelectionChanged(ObservableCollection<Object> itemList, int index, View child, boolean selected);

		void onItemSelected(Object obj, int index, View child);

		void onFocusChanged(View child, boolean hasFocus);
		void onRowFocusChanged(boolean hasFocus);
	}

	public OnSelectionChangeListener changeListener;
	
	public BindableVideoStripLayout(Context context) {
		this(context, null);
	}

	public BindableVideoStripLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public BindableVideoStripLayout(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		return new ViewGroup.MarginLayoutParams(getContext(), attrs);
	}

	@Override
	protected ViewGroup.MarginLayoutParams generateDefaultLayoutParams() {
		return new ViewGroup.MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	}

	@Override
	protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
		return new LayoutParams(p);
	}

	public void setBlobListener(IFocusHopperListener listener) {
		this.listener = listener;
	}

	public void setOnSelectionChangeListener(OnSelectionChangeListener listener) {
		this.changeListener = listener;
	}

	private void init() {
		setFocusable(true);
		setClickable(true);
		setOnFocusChangeListener(this);
		setScrollContainer(true);
		mOffsetLeft = 60;
		setDescendantFocusability(FOCUS_BEFORE_DESCENDANTS);
	}

	boolean needScrollCorrection = false;
	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if (getChildCount() == 0)
			return;
		needScrollCorrection = true;
		for (int i = 0; i < getChildCount(); i++) {
//			notifyFocusChanged(i, hasFocus);
			notifySelectionChanged(i, (i == mSelectedIndex) && hasFocus);
		}

		notifyRowFocusChanged(hasFocus);

		if (hasFocus) {
			setChildSelections();
		} else {
			unsetChildSelections();
		}
	}

	@Override
	protected Parcelable onSaveInstanceState() {
		Bundle bundle = new Bundle();
		bundle.putParcelable("instanceState", super.onSaveInstanceState());
		bundle.putInt("mPreviousSelectedIndex", mPreviousSelectedIndex);
		bundle.putInt("mSelectedIndex", mSelectedIndex);
		return bundle;
	}

	@Override
	protected void onRestoreInstanceState(Parcelable state) {
		if (state instanceof Bundle) {
			Bundle bundle = (Bundle) state;
			this.mPreviousSelectedIndex = bundle.getInt("mPreviousSelectedIndex");
			this.mSelectedIndex = bundle.getInt("mSelectedIndex");
			super.onRestoreInstanceState(bundle.getParcelable("instanceState"));
			return;
		}

		super.onRestoreInstanceState(state);
	}

	private WeakList<Object> currentList = null;
	private ObservableCollection<Object> itemList = null;
	private CollectionObserver collectionObserver = new CollectionObserver() {
		@Override
		public void onCollectionChanged(IObservableCollection<?> collection, CollectionChangedEventArg args, Collection<Object> initiators) {
			listChanged(args, collection);
		}
	};

	private void createItemSourceList(ObservableCollection<Object> newList) {
		if (itemList != null && collectionObserver != null)
			itemList.unsubscribe(collectionObserver);

		itemList = newList;

		if (newList == null)
			return;

		currentList = null;

		itemList.subscribe(collectionObserver);
		newList(newList);
	}

	public ObservableCollection<Object> getItemList(){
		return itemList;
	}

	@Override
	protected void onDetachedFromWindow() {
		currentList = null;
		super.onDetachedFromWindow();
	}

	@Override
	public ViewAttribute<BindableVideoStripLayout, ?> createViewAttribute(String attributeId) {
		if (attributeId.equals("itemSource"))
			return ItemSourceAttribute;
		if (attributeId.equals("itemLayout"))
			return ItemLayoutAttribute;
		return null;
	}

	private void newList(IObservableCollection<?> list) {
		this.removeAllViews();
		observableItemsLayoutID.clear();
		if (list == null) {
			currentList = null;
		} else {
			currentList = new WeakList<Object>();
			for (int pos = 0; pos < list.size(); pos++) {
				Object item = list.getItem(pos);
				// Log.e(VideoStripLayout.class.getName(),
				// "item " + item.toString());
				insertItem(pos, item);
				currentList.add(item);
			}
		}
	}

	private void listChanged(CollectionChangedEventArg e, IObservableCollection<?> collection) {
		if (e == null)
			return;

		int pos = -1;
		switch (e.getAction()) {
		case Add:
			pos = e.getNewStartingIndex();
			for (Object item : e.getNewItems()) {
				insertItem(pos, item);
				pos++;
			}
			break;
		case Remove:
			removeItems(e.getOldItems());
			break;
		case Replace:
			removeItems(e.getOldItems());
			pos = e.getNewStartingIndex();
			if (pos < 0)
				pos = 0;
			for (Object item : e.getNewItems()) {
				insertItem(pos, item);
				pos++;
			}
			break;
		case Reset:
			mSelectedIndex = 0;
			mPreviousSelectedIndex = 0;
			scrollTo(0,0);
			newList(collection);
			break;
		case Move:
			// currently the observable array list doesn't create this action
			throw new IllegalArgumentException("move not implemented");
		default:
			throw new IllegalArgumentException("unknown action " + e.getAction().toString());
		}

		if (collection == null)
			return;

		currentList = new WeakList<Object>();
		for (pos = 0; pos < collection.size(); pos++) {
			Object item = collection.getItem(pos);
			currentList.add(item);
		}
	}

	private ViewAttribute<BindableVideoStripLayout, Object> ItemSourceAttribute = new ViewAttribute<BindableVideoStripLayout, Object>(
			Object.class, BindableVideoStripLayout.this, "ItemSource") {
		@SuppressWarnings("unchecked")
		@Override
		protected void doSetAttributeValue(Object newValue) {
			if (!(newValue instanceof ObservableCollection<?>))
				return;

			if (layout != null)
				createItemSourceList((ObservableCollection<Object>) newValue);
		}

		@Override
		public Object get() {
			return itemList;
		}
	};

	private LayoutItem layout = null;

	private ViewAttribute<BindableVideoStripLayout, Object> ItemLayoutAttribute = new ViewAttribute<BindableVideoStripLayout, Object>(
			Object.class, BindableVideoStripLayout.this, "ItemLayout") {
		@Override
		protected void doSetAttributeValue(Object newValue) {
			layout = null;
			if (newValue instanceof LayoutItem) {
				layout = (LayoutItem) newValue;
			} else if (newValue instanceof Layout) {
				layout = new LayoutItem(((Layout) newValue).getDefaultLayoutId());
			} else if (newValue instanceof Integer) {
				layout = new LayoutItem((Integer) newValue);
			} else {
				layout = new LayoutItem(newValue.toString());
			}

			if (itemList != null)
				createItemSourceList(itemList);
		}

		@Override
		public Object get() {
			return layout;
		}
	};

	private ObservableMultiplexer<Object> observableItemsLayoutID = new ObservableMultiplexer<Object>(
			new Observer() {
				@Override
				public void onPropertyChanged(IObservable<?> prop,
						Collection<Object> initiators) {
					if (initiators == null || initiators.size() < 1)
						return;
					Object parent = initiators.toArray()[0];
					int pos = currentList.indexOf(parent);
					ArrayList<Object> list = new ArrayList<Object>();
					list.add(parent);
					removeItems(list);
					insertItem(pos, parent);
				}
			});

	private void removeItems(List<?> deleteList) {
		if (deleteList == null || deleteList.size() == 0 || currentList == null)
			return;

		ArrayList<Object> currentPositionList = new ArrayList<Object>(Arrays.asList(currentList.toArray()));

		for (Object item : deleteList) {
			int pos = currentPositionList.indexOf(item);
			observableItemsLayoutID.removeParent(item);
			currentPositionList.remove(item);
			if (pos > -1 && pos < this.getChildCount())
				this.removeViewAt(pos);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void insertItem(int pos, Object item) {
		if (layout == null)
			return;

		int layoutId = layout.getLayoutId();
		if (layoutId < 1 && layout.getLayoutName() != null) {
			IObservable<?> observable = null;
			InnerFieldObservable ifo = new InnerFieldObservable(layout.getLayoutName());
			if (ifo.createNodes(item)) {
				observable = ifo;
			} else {
				Object rawField;
				try {
					rawField = Binder.getSyntaxResolver().getFieldForModel(layout.getLayoutName(), item);
				} catch (SyntaxResolveException e) {
					BindingLog.exception("BindableLinearLayout.insertItem()", e);
					return;
				}
				if (rawField instanceof IObservable<?>)
					observable = (IObservable<?>) rawField;
				else if (rawField != null)
					observable = new ConstantObservable(rawField.getClass(), rawField);
			}

			if (observable != null) {
				observableItemsLayoutID.add(observable, item);
				Object obj = observable.get();
				if (obj instanceof Integer)
					layoutId = (Integer) obj;
			}
		}
		View child = null;

		if (layoutId < 1) {
			TextView textView = new TextView(getContext());
			textView.setText("binding error - pos: " + pos + " has no layout - please check binding:itemPath or the layout id in viewmodel");
			textView.setTextColor(Color.RED);
			child = textView;
		} else {
			Binder.InflateResult result = Binder.inflateView(getContext(), layoutId, this, false);
			for (View view : result.processedViews) {
				AttributeBinder.getInstance().bindView(getContext(), view, item);
			}

			child = result.rootView;
		}

		this.addView(child, pos);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		measureChildren(widthMeasureSpec, heightMeasureSpec);
		int specModeW = MeasureSpec.getMode(widthMeasureSpec);
		int specSizeW = MeasureSpec.getSize(widthMeasureSpec);

		int specModeH = MeasureSpec.getMode(heightMeasureSpec);
		int specSizeH = MeasureSpec.getSize(heightMeasureSpec);
		int maxWidth = specSizeW;
		int maxHeight = specSizeH;
		int maxChildHeight = 0;
		int totalWidth = mOffsetLeft;
		ViewGroup.MarginLayoutParams mlp = (MarginLayoutParams) getLayoutParams();
		for (int i = 0; i < getChildCount(); i++) {
			View child = getChildAt(i);
			final ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();

			int vHeight = child.getMeasuredHeight() + lp.bottomMargin + lp.topMargin;
			int vWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
			maxChildHeight = Math.max(maxChildHeight, vHeight);
			totalWidth += vWidth;
		}

		maxChildHeight += mlp.topMargin + mlp.bottomMargin;
		if (specModeH == MeasureSpec.UNSPECIFIED) {
			maxHeight = maxChildHeight;
		} else if (specModeH == MeasureSpec.AT_MOST) {
			maxHeight = Math.min(maxHeight, maxChildHeight);
		}

		maxWidth = totalWidth;
		setMeasuredDimension(maxWidth, maxHeight);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		final int paddingTop = getPaddingTop();
		if(needScrollCorrection) {
			View selectedChild = getChildAt(mSelectedIndex);
			if(selectedChild != null)
				scrollTo(mSelectedIndex * selectedChild.getMeasuredWidth(),0);
			needScrollCorrection = false;
			pendingList.clear();
		}
		int childTop = paddingTop;
		int childLeft = mOffsetLeft;
		mItemCount = getChildCount();

		childLeft = mOffsetLeft;
		for (int i = 0; i < mItemCount; i++) {
			int childIndex = i;
			final View child = getChildAt(childIndex);
			final int childWidth = child.getMeasuredWidth();
			final int childHeight = child.getMeasuredHeight();

			final ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
			childTop = getHeight() - childHeight - lp.bottomMargin;
			childLeft += (lp.leftMargin);
			setChildFrame(child, childLeft, childTop, childWidth, childHeight);
			childLeft += (childWidth + lp.rightMargin);
		}
	}

	private void setChildFrame(View child, int left, int top, int width, int height) {
		child.layout(left, top, left + width, top + height);
	}

	void measureChildBeforeLayout(View child, int childIndex, int widthMeasureSpec, int totalWidth, int heightMeasureSpec, int totalHeight) {
		measureChildWithMargins(child, widthMeasureSpec, totalWidth, heightMeasureSpec, totalHeight);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if(!executeKeyEvent(event))
			return super.dispatchKeyEvent(event);
		else
			return true;
	}

	boolean mReceivedInvokeKeyDown;

	public boolean executeKeyEvent(KeyEvent event) {
		boolean handled = false;
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			switch (event.getKeyCode()) {

				case KeyEvent.KEYCODE_DPAD_LEFT:

					if (movePrevious()) {
						setChildSelections();
						handled = true;
					}
					break;

				case KeyEvent.KEYCODE_DPAD_RIGHT:
					if (moveNext()) {
						setChildSelections();
					}
					handled = true;
					break;

				case KeyEvent.KEYCODE_DPAD_CENTER:
				case KeyEvent.KEYCODE_ENTER:
					mReceivedInvokeKeyDown = true;
					if (this.changeListener != null) {
						View child = getChildAt(mSelectedIndex);
						Object obj = itemList.getItem(mSelectedIndex);
						changeListener.onItemSelected(obj, mSelectedIndex, child);
					}
					handled = true;
					break;
			}
		}
		return handled;
	}

	boolean movePrevious() {
		boolean status = false;
		if (mItemCount > 0 && mSelectedIndex > 0) {
			mPreviousSelectedIndex = mSelectedIndex;
			mSelectedIndex--;
			status = true;
			pendingList.add(new Point(mPreviousSelectedIndex, mSelectedIndex));
		}
		triggerSlide();
		return status;
	}

	boolean moveNext() {
		boolean status = false;
		if (mItemCount > 0 && mSelectedIndex < mItemCount - 1) {
			mPreviousSelectedIndex = mSelectedIndex;
			mSelectedIndex++;
			status = true;
			pendingList.add(new Point(mPreviousSelectedIndex, mSelectedIndex));
		}
		triggerSlide();
		return status;
	}

	private void notifySelectionChanged(int index, boolean selected) {
		View child = getChildAt(index);
		if (this.changeListener != null) {
			changeListener.onSelectionChanged(itemList, index, child, selected);
		}
	}

	private void notifyFocusChanged(int index, boolean hasFocus) {
		if (this.changeListener != null) {
			View child = getChildAt(index);
			changeListener.onFocusChanged(child, hasFocus);
		}
	}

	private void notifyRowFocusChanged(boolean hasFocus) {
		if (this.changeListener != null) {
			changeListener.onRowFocusChanged(hasFocus);
		}
	}

	void setChildSelections() {
		View view;

		view = getChildAt(mPreviousSelectedIndex);
		if (view != null)
			view.setSelected(false);

		view = getChildAt(mSelectedIndex);
		if (view != null)
			view.setSelected(true);
	}

	void unsetChildSelections() {
		View view;
		view = getChildAt(mPreviousSelectedIndex);
		if (view != null)
			view.setSelected(false);

		view = getChildAt(mSelectedIndex);
		if (view != null)
			view.setSelected(false);
	}

	private void triggerSlide() {
		ArrayList<Animator> animset = new ArrayList<Animator>();
		while (pendingList.size() > 0) {
			Point p = pendingList.poll();
			int prevIndex = p.x;
			int nextIndex = p.y;
			final View previousChild = getChildAt(prevIndex);
			final View nextChild = getChildAt(nextIndex);
			if(previousChild == null || nextChild == null)
				break;
			notifySelectionChanged(prevIndex, false);
			ObjectAnimator anim;
			if (prevIndex < nextIndex) {
				anim = ObjectAnimator.ofFloat(previousChild, "alpha", 0.25f);
			} else {
				anim = ObjectAnimator.ofFloat(nextChild, "alpha", 1.0f);
			}
			anim.setDuration(ANIMATION_DURATION_SLIDE_LEFT_RIGHT);
			anim.setInterpolator(new AccelerateInterpolator());
			animset.add(anim);

			// add this for animation
			if(pendingList.size() == 0) {
				pendingList.add(new Point(prevIndex, nextIndex));
				break;
			}
		}

		AnimatorSet set = new  AnimatorSet();
		set.playTogether(animset);
		set.start();
		notifySelectionChanged(mSelectedIndex, true);

		View nChild = getChildAt(mSelectedIndex);
		if(nChild != null) {
			if((va == null ) || (va != null && !va.isRunning())) {
				slide(nChild);
			}
		}
	}

	ValueAnimator va;

	public boolean slide(View selectedChild) {
		if (selectedChild == null) {
			selectedChild = getChildAt(mSelectedIndex);
		}

		int distance = getScrollX() - selectedChild.getLeft() + mOffsetLeft;
		if (distance == 0)
			return false;

		va = ValueAnimator.ofInt(0, distance);
		va.setDuration(ANIMATION_DURATION_SLIDE_LEFT_RIGHT);
		va.setInterpolator(new AccelerateDecelerateInterpolator());
		va.addUpdateListener(new AnimatorUpdateListener() {
			int lastValue = 0;
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				int amount = (Integer) animation.getAnimatedValue() - lastValue;
				scrollBy(-amount, 0);
				lastValue = (Integer) animation.getAnimatedValue();
			}
		});

		AnimatorListener animationListener = new AnimatorListener() {
			@Override
			public void onAnimationCancel(Animator animation) {
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				va.removeAllListeners();

				if(pendingList.size() > 0) {
					Point p = pendingList.poll();
					final View nchild = getChildAt(p.y);
					slide(nchild);
				}else {
					final View nchild = getChildAt(mSelectedIndex);
					int distance = getScrollX() - nchild.getLeft() + mOffsetLeft;
					if (Math.abs(distance) > 5) {
						needScrollCorrection = true;
						slide(nchild);
					}else {
						onFinishedMovement();
					}
				}
			}

			@Override
			public void onAnimationRepeat(Animator animation) {
			}

			@Override
			public void onAnimationStart(Animator animation) {
			}
		};
		va.addListener(animationListener);
		va.start();
		return true;
	}

	private void onFinishedMovement() {
		notifySelectionChanged(mPreviousSelectedIndex, false);
		notifySelectionChanged(mSelectedIndex, true);
	}

}
