/**
 * Project Name:ulewo-common
 * File Name:FileUtils.java
 * Package Name:com.ulewo.utils
 * Date:2015年11月29日下午4:25:49
 * Copyright (c) 2015, ulewo.com All Rights Reserved.
 *
*/

package com.crane.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * ClassName:FileUtils <br/>
 * Date:     2015年11月29日 下午4:25:49 <br/>
 * @author   多多洛
 * Copyright (c) 2015, ulewo.com All Rights Reserved. 
 */
public class FileUtils {
	public static void copyFile(File sourcefile, File targetFile) {
		FileInputStream fi = null;

		FileOutputStream fo = null;

		FileChannel in = null;

		FileChannel out = null;

		try {

			fi = new FileInputStream(sourcefile);

			fo = new FileOutputStream(targetFile);

			in = fi.getChannel();//得到对应的文件通道

			out = fo.getChannel();//得到对应的文件通道

			in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				fi.close();

				in.close();

				fo.close();

				out.close();

			} catch (IOException e) {

				e.printStackTrace();

			}

		}
	}
}
