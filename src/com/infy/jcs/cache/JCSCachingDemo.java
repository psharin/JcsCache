package com.infy.jcs.cache;


import org.apache.jcs.admin.CacheElementInfo;
import org.apache.jcs.engine.control.CompositeCache;

import com.infy.jcs.cache.manager.JCSGenericCacheManager;
import com.infy.jcs.cache.vo.StudentVO;

/**
 * Main class to test JCS Cache.
 */
public class JCSCachingDemo {

	/**
	 * Main method.
	 */
	public static void main(final String[] args) {
		new JCSCachingDemo();
	}

	/**
	 * Cache Manager.
	 */
	public JCSGenericCacheManager<StudentVO> jcsGenericCacheManager = null;

	/**
	 * Default constructor.
	 */
	public JCSCachingDemo() {
		try {
			this.jcsGenericCacheManager = JCSGenericCacheManager
					.getInstance(StudentVO.class);
		} catch (final Exception e) {
			e.printStackTrace();
		}

		this.loadCacheForDemo();
		this.showCacheRegionsMetadata();
		this.showCachedItemsMetadata();

		try {
			System.out.println("===== GET CALLED =====");
			StudentVO studentVO1 = this.jcsGenericCacheManager.getItem("" + 1);
			System.out.println("ID: " + studentVO1.getId() + "\tName: "
					+ studentVO1.getStudentName() + "\tAge: "
					+ studentVO1.getStudentAge() + "\tYear: "
					+ studentVO1.getStudentYear() + "\tAddress: "
					+ studentVO1.getStudentAddress());
			StudentVO studentVO2 = this.jcsGenericCacheManager.getItem("" + 3);
			System.out.println("ID: " + studentVO2.getId() + "\tName: "
					+ studentVO2.getStudentName() + "\tAge: "
					+ studentVO2.getStudentAge() + "\tYear: "
					+ studentVO2.getStudentYear() + "\tAddress: "
					+ studentVO2.getStudentAddress());

			this.showCacheRegionsMetadata();
			this.showCachedItemsMetadata();

			System.out.println("===== REMOVE CALLED =====");
			this.jcsGenericCacheManager.removeItem("" + 3);
			this.showCacheRegionsMetadata();
			this.showCachedItemsMetadata();

			System.out.println("===== CLEAR CALLED =====");
			this.jcsGenericCacheManager.clearCache();
			this.showCacheRegionsMetadata();
			this.showCachedItemsMetadata();

			System.out.println("===== DISPOSE CALLED =====");
			this.jcsGenericCacheManager.disposeCache();
			this.showCacheRegionsMetadata();
			this.showCachedItemsMetadata();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Loading the JCS Cache.
	 */
	private void loadCacheForDemo() {
		if (this.jcsGenericCacheManager != null) {
			final StudentVO studentVO_1 = new StudentVO(1, "Bhavesh Thaker",
					30, "India", 2010);
			final StudentVO studentVO_2 = new StudentVO(2, "Rational Java", 25,
					"USA", 2011);
			final StudentVO studentVO_3 = new StudentVO(3, "Blogspot", 22,
					"UK", 2009);
			final StudentVO studentVO_4 = new StudentVO(4, "JCS Cache", 28,
					"India", 2008);

			try {
				this.jcsGenericCacheManager.putItem("" + studentVO_1.getId(),
						studentVO_1);
				this.jcsGenericCacheManager.putItem("" + studentVO_2.getId(),
						studentVO_2);
				this.jcsGenericCacheManager.putItem("" + studentVO_3.getId(),
						studentVO_3);
				this.jcsGenericCacheManager.putItem("" + studentVO_4.getId(),
						studentVO_4);
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Getting the Cached Items Meta-Data.
	 */
	private void showCachedItemsMetadata() {
		try {
			final CacheElementInfo[] cacheElementInfoArray = this.jcsGenericCacheManager
					.getCachedItemsMetadata();
			for (final CacheElementInfo element : cacheElementInfoArray) {
				System.out.println("Key: " + element.getKey());
				System.out.println("Creation Time: " + element.getCreateTime());
				System.out.println("Maximum Life (seconds): "
						+ element.getMaxLifeSeconds());
				System.out.println("Expires in (seconds): "
						+ element.getExpiresInSeconds());
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Getting the Cached Region Meta-Data.
	 */
	private void showCacheRegionsMetadata() {
		try {
			final CompositeCache[] compositeCacheArray = this.jcsGenericCacheManager
					.getCacheRegionsMetadata();
			for (final CompositeCache element : compositeCacheArray) {
				System.out.println("Cache Name: " + element.getCacheName());
				System.out.println("Cache Type: " + element.getCacheType());
				System.out.println("Cache Misses (not found): "
						+ element.getMissCountNotFound());
				System.out.println("Cache Misses (expired): "
						+ element.getMissCountExpired());
				System.out.println("Cache Hits (memory): "
						+ element.getHitCountRam());
				System.out
						.println("Cache Updates: " + element.getUpdateCount());
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

}