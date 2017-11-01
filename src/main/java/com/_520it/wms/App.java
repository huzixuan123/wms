package com._520it.wms;

import java.io.File;

import org.junit.Test;

import net.coobird.thumbnailator.Thumbnails;

public class App {
	@Test
	public void testName() throws Exception {
		Thumbnails.of(new File("D:/02.jpg"))
			.scale(0.4)
				.toFile(new File("D:/mm.jpg"));
		;
	}
}
