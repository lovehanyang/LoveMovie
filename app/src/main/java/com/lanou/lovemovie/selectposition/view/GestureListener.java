package com.lanou.lovemovie.selectposition.view;

import java.util.ArrayList;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;


class GestureListener extends GestureDetector.SimpleOnGestureListener {
	private SSView mSsView;

	GestureListener(SSView paramSSView) {
		mSsView = paramSSView;
	}

	public boolean onDoubleTap(MotionEvent paramMotionEvent) {
		return super.onDoubleTap(paramMotionEvent);
	}

	public boolean onDoubleTapEvent(MotionEvent paramMotionEvent) {
		return super.onDoubleTapEvent(paramMotionEvent);
	}

	public boolean onDown(MotionEvent paramMotionEvent) {
		return false;
	}

	public boolean onFling(MotionEvent paramMotionEvent1,
			MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2) {
		return false;
	}

	public void onLongPress(MotionEvent paramMotionEvent) {
	}

	public boolean onScroll(MotionEvent paramMotionEvent1,
			MotionEvent paramMotionEvent2, float x_scroll_distance, float y_scroll_distance) {
		//�Ƿ�����ƶ��͵��
		if(!SSView.a(mSsView)){
			return false;
		}
		//��ʾ����ͼ
		SSView.a(mSsView,true);
		boolean bool1 = true;
		boolean bool2 = true;
		if ((SSView.s(mSsView) < mSsView.getMeasuredWidth())
				&& (0.0F == SSView.v(mSsView))){
			bool1 = false;
		}
		
		if ((SSView.u(mSsView) < mSsView.getMeasuredHeight())
				&& (0.0F == SSView.w(mSsView))){
			bool2  = false;
		}
		
		if(bool1){
			int k = Math.round(x_scroll_distance);
			//�޸�����x���ƫ����
			SSView.c(mSsView, (float)k);
//			Log.i("TAG", SSView.v(mSsView)+"");
			//�޸���λ��������ĺ������
			SSView.k(mSsView, k);
//			Log.i("TAG", SSView.r(mSsView)+"");
			if (SSView.r(mSsView) < 0) {
				//��������
				SSView.i(mSsView, 0);
				SSView.a(mSsView, 0.0F);
			}
			
			if(SSView.r(mSsView) + mSsView.getMeasuredWidth() > SSView.s(mSsView)){
				//��������
				SSView.i(mSsView, SSView.s(mSsView) - mSsView.getMeasuredWidth());
				SSView.a(mSsView, (float)(mSsView.getMeasuredWidth() - SSView.s(mSsView)));
			}
		}
		
		if(bool2){
			//�ϸ�����- ���»����
			int j = Math.round(y_scroll_distance);
			//�޸�����y���ƫ����
			SSView.d(mSsView, (float)j);
			//�޸Ŀ�����λ���붥�˵ľ���
			SSView.l(mSsView, j);
			Log.i("TAG", SSView.t(mSsView)+"");
			if (SSView.t(mSsView) < 0){
				//������
				SSView.j(mSsView, 0);
				SSView.b(mSsView, 0.0F);
			}
			
			 if (SSView.t(mSsView) + mSsView.getMeasuredHeight() > SSView
						.u(mSsView)){
				//������
					SSView.j(mSsView, SSView.u(mSsView) - mSsView.getMeasuredHeight());
					SSView.b(mSsView, (float)(mSsView.getMeasuredHeight() - SSView.u(mSsView)));
			 }
		}
		
		mSsView.invalidate();
		
//		Log.i("GestureDetector", "onScroll----------------------");
		return false;
	}

	public void onShowPress(MotionEvent paramMotionEvent) {
	}

	public boolean onSingleTapConfirmed(MotionEvent paramMotionEvent) {
		return false;
	}

	public boolean onSingleTapUp(MotionEvent paramMotionEvent) {
//		Log.i("GestureDetector", "onSingleTapUp");
//		if(!SSView.a(mSsView)){
//			return false;
//		}
		//����
		int i = SSView.a(mSsView, (int)paramMotionEvent.getX());
		//����
		int j = SSView.b(mSsView, (int) paramMotionEvent.getY());
	
		if((j>=0 && j< SSView.b(mSsView).size())){
			if(i>=0 && i<((ArrayList<Integer>)(SSView.b(mSsView).get(j))).size()){
				Log.i("TAG", "����"+ j + "����"+i);
				ArrayList<Integer> localArrayList = (ArrayList<Integer>) SSView.b(mSsView).get(j);
				switch (localArrayList.get(i).intValue()) {
				case 3://��ѡ��
					localArrayList.set(i, Integer.valueOf(1));
					if(SSView.d(mSsView)!=null){
						SSView.d(mSsView).a(i, j, false);
					}
					
					
					
					break;
				case 1://��ѡ
					localArrayList.set(i, Integer.valueOf(3));
					if(SSView.d(mSsView)!=null){
						SSView.d(mSsView).b(i, j, false);
					}
					break;
				default:
					break;
				}
				
			}
		}
		
		
		
		
		
		
		
		
		//��ʾ����ͼ
		SSView.a(mSsView,true);
		mSsView.invalidate();
		return false;
	}
}