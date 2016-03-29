/**
 * Copyright (c) www.longdw.com
 */
package com.lanou.lovemovie.dock.listen.musicplayer.uimanager;

import android.view.View;

public abstract class MainUIManager {
	
	protected abstract void setBgByPath(String path);
	public abstract View getView();
	public abstract View getView(int from);
	public abstract View getView(int from, Object obj);
}
