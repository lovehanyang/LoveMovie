/**
 * Copyright (c) www.longdw.com
 */
package com.lanou.lovemovie.dock.listen.musicplayer;

import com.lanou.lovemovie.MusicInfo;

import java.util.List;



public interface IQueryFinished {
	
	public void onFinished(List<MusicInfo> list);

}
