package com.winjean.design_pattern.configuration.adapter;

/**
 * 项目名称：重庆微警务（一期）
 * 类名称：<....>
 * 类描述：<....>
 * 创建人：Administrator
 * 创建时间：2018/10/24 8:54
 * 修改人：Administrator
 * 修改时间：2018/10/24 8:54
 * 修改备注：
 * 版权所有权：江苏艾盾网络科技有限公司
 *
 * @version V1.0
 */
public class AudioPlayer implements MediaPlayer  {
    MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("mp3")){
            //播放 mp3 音乐文件的内置支持
            System.out.println("Playing mp3 file. Name: "+ fileName);
        } else if( audioType.equalsIgnoreCase("mp4")){
            //mediaAdapter 提供了播放其他文件格式的支持
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        } else{
            System.out.println("Invalid media. " + audioType + " format not supported");
        }
    }
}
