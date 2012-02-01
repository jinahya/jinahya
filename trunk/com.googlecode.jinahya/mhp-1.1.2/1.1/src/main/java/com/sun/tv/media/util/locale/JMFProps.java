/*
 * @(#)JMFProps.java	1.34 98/12/09
 *
 * Copyright 1996-1998 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 */

package com.sun.tv.media.util.locale;

/**
 * This class is a part of the porting layer implementation for JavaTV.
*/
public class JMFProps extends java.util.ListResourceBundle  {

 	public Object[][] getContents(){
		return contents;
	}

	static final Object[][] contents = {

/*A.D
	{"moviesourcenode.0_kbps" , "0 kbps"},
	{"moviesourcenode.unsupported/disabled" , " Unsupported/Disabled"},
	{"moviesourcenode.stereo.string" , "stereo "},
	{"moviesourcenode.mono.string" , "mono "},
	{"moviesourcenode.none" , "None"},
	{"moviesourcenode.khz" , " KHz "},
	{"moviesourcenode.kbps" , " kbps"},
	{"moviesourcenode.-bit" , "-bit"},
	{"mediaplayer.N/A" , " N/A"},
	{"mediaplayer.version" , "Version 1.1"},
	{"mediaplayer.download", "Loading media:"},
	{"mediaplayer.rate", "Rate"},
	{"mediaplayer.rate.tenth", "1/10 speed"},
	{"mediaplayer.rate.half", "Half speed"},
	{"mediaplayer.rate.normal", "Normal speed"},
	{"mediaplayer.rate.double", "Double speed"},
	{"mediaplayer.rate.triple", "Triple speed"},
	{"mediaplayer.rate.quadruple", "Quadruple speed"},
	{"propertysheet.close", "Close"},
	{"propertysheet.unknown", "Unknown"},
	{"propertysheet.url", "URL:"},
	{"propertysheet.duration", "Duration:"},
	{"propertysheet.bit.rate", "Bit rate:"},
	{"propertysheet.video", "Video:"},
	{"propertysheet.video.codec", "Encoding:"},
	{"propertysheet.video.size", "Size:"},
	{"propertysheet.video.rate", "Rate:"},
	{"propertysheet.audio", "Audio:"},
	{"propertysheet.audio.codec", "Encoding:"},
	{"propertysheet.audio.quality", "Quality:"},
	{"propertysheet.JavaMediaPlayer", "Java Media Player"},
	{"propertysheet.JMF.version", "JMF version:"},
	{"propertysheet.brightness", "brightness:"},
	{"propertysheet.contrast", "contrast:"},
	{"propertysheet.saturation", "saturation:"},
	{"propertysheet.hue", "hue:"},
	{"streamingaudiosourcenode.rtp_audio" , "RTP Audio"},
	{"streamingaudiosourcenode.kbps" , " kbps"},
	{"streamingaudiosourcenode.rtp" , "RTP "},
	{"streamingaudiosourcenode.rtp_gsm" , "RTP GSM"},
	{"streamingaudiosourcenode.0_kbps" , "0 kbps"},
	{"streamingaudiosourcenode.rtp_g711" , "RTP G711"},
	{"streamingaudiosourcenode.rtp_g723" , "RTP G723"},
	{"streamingaudiosourcenode.khz" , " KHz"},
	{"streamingsourcenode.rtp" , "RTP"},
	{"streamingsourcenode.kbps" , " kbps"},
	{"streamingsourcenode.rtp_jpeg" , "RTP JPEG"},
        {"streamingsourcenode.rtp_h261" , "RTP H261"},
	{"streamingsourcenode.rtp_h263" , "RTP H263"},
	{"streamingsourcenode.0_kbps" , "0 kbps"},
	{"videorenderer.frames/sec" , " frames/sec"},
	{"jmpx.MPEG1-Audio", "MPEG-1 Audio"},
	{"jmpx.MPEG-1", " MPEG-1"},
	{"codec.linear", "Linear"},
	{"codec.unknown", "Unknown"},
	{"codec.ulaw", "G.711 ulaw"},
	{"codec.g711_ulaw", "G.711 ulaw"},
	{"codec.g711_alaw", "G.711 alaw"},
	{"codec.g722", "G.722"},
	{"codec.g723", "G.723"},
	{"codec.g721_adpcm", "G.721 ADPCM"},
	{"codec.dbi_adpcm", "DBI ADPCM"},
	{"codec.oki_adpcm", "OKI ADPCM"},
	{"codec.msadpcm", "MS ADPCM"},
	{"codec.digistd", "digistd"},
	{"codec.digifix", "digifix"},
	{"codec.dvi", "DVI ADPCM"},
	{"codec.ima4", "IMA4 ADPCM"},
	{"codec.linear8", "Linear 8-bit"},
	{"codec.mac6", "Mac 6"},
	{"codec.mac3", "Mac 3"},
	{"codec.gsm", "GSM"},
	{"codec.sx7383", "SX7383"},
	{"codec.cvid", "Cinepak"},
	{"codec.iv32", "Indeo 3.2"},
	{"codec.iv41", "Indeo 4.1"},
	{"codec.iv50", "Indeo 5.0"},
	{"codec.rle", "RLE"},
	{"codec.raw", "None"},
	{"codec.h263", "H.263"},
	{"codec.h261", "H.261"},
	{"codec.mpeg", "MPEG"},
	{"codec.jpeg", "JPEG"},
	{"codec.smc", "SMC"},
	{"error.connectionerror", "connection error"},
	{"error.filenotfound", "file does not exist or is unreadable"},
        {"error.urlnotfound", "URL does not exist or is unreadable"},

/** Don't translate the following strings **/
{"jmf.properties", "jmf.properties"},
	{"jmf.default.font", "Helvetica"}

	};
}