package com.infy.jcs.cache.manager;



import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;
import org.apache.jcs.admin.CacheElementInfo;
import org.apache.jcs.admin.CacheRegionInfo;
import org.apache.jcs.admin.JCSAdminBean;
import org.apache.jcs.engine.control.CompositeCache;

/**
 * Generic JCS Cache Manager which can hold any collection of Java Objects.
 */
public class JCSGenericCacheManager<T> {

	/**
	 * JCS object.
	 */
	private static JCS CACHE = null;
	/**
	 * JCS region name - default.
	 */
	private static String CACHE_REGION_NAME = "default";
	/**
	 * Map holding multiple caches for each kind of Java Objects.
	 */
	private static Map<Class<?>, Object> instanceMap = new HashMap<Class<?>, Object>();

	static {
		try {
			JCSGenericCacheManager.CACHE = JCS
					.getInstance(JCSGenericCacheManager.CACHE_REGION_NAME);
		} catch (final CacheException e) {
			e.printStackTrace();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the JCS Cache Manager's instance.
	 */
	@SuppressWarnings("unchecked")
	public static <T> JCSGenericCacheManager<T> getInstance(
			final Class<T> classObject) throws Exception {
		T instance = (T) JCSGenericCacheManager.instanceMap.get(classObject);
		if (instance != null) {
			return (JCSGenericCacheManager<T>) instance;
		}

		try {
			synchronized (JCSGenericCacheManager.class) {
				instance = (T) new JCSGenericCacheManager<T>();
				JCSGenericCacheManager.instanceMap.put(classObject, instance);
			}
		} catch (final ClassCastException classCastException) {
			throw new IllegalArgumentException(classCastException);
		}

		return (JCSGenericCacheManager<T>) instance;
	}

	/**
	 * Private constructor.
	 */
	private JCSGenericCacheManager() throws Exception {
		if (JCSGenericCacheManager.CACHE == null) {
			throw new Exception("JCS Cache not initialized");
		}
	}

	/**
	 * Clear JCS Cache.
	 */
	public void clearCache() {
		try {
			JCSGenericCacheManager.CACHE.clear();
		} catch (final CacheException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Dispose JCS Cache.
	 */
	public void disposeCache() {
		JCSGenericCacheManager.CACHE.dispose();
	}

	/**
	 * Gets the JCS Cached items meta data.
	 */
	@SuppressWarnings("unchecked")
	public CacheElementInfo[] getCachedItemsMetadata() throws Exception {
		CacheElementInfo[] cacheElementInfoArray = null;
		int i = 0;

		final JCSAdminBean jcsAdminBean = new JCSAdminBean();
		final LinkedList<CacheElementInfo> cacheElementInfoLinkedList = jcsAdminBean
				.buildElementInfo(JCSGenericCacheManager.CACHE_REGION_NAME);

		cacheElementInfoArray = new CacheElementInfo[cacheElementInfoLinkedList
				.size()];

		for (final CacheElementInfo cacheElementInfo : cacheElementInfoLinkedList) {
			cacheElementInfoArray[i] = cacheElementInfo;
			i++;
		}
		return cacheElementInfoArray;
	}

	/**
	 * Gets the JCS Cache Regions meta data.
	 */
	@SuppressWarnings("unchecked")
	public CompositeCache[] getCacheRegionsMetadata() throws Exception {
		CompositeCache[] compositeCacheArray = null;
		int i = 0;

		final JCSAdminBean jcsAdminBean = new JCSAdminBean();
		final LinkedList<CacheRegionInfo> cacheRegionInfoLinkedList = jcsAdminBean
				.buildCacheInfo();

		compositeCacheArray = new CompositeCache[cacheRegionInfoLinkedList
				.size()];

		for (final CacheRegionInfo cacheRegionInfo : cacheRegionInfoLinkedList) {
			final CompositeCache compositeCache = cacheRegionInfo.getCache();
			compositeCacheArray[i] = compositeCache;
			i++;
		}

		return compositeCacheArray;
	}

	/**
	 * Get item from JCS Cache.
	 */
	@SuppressWarnings("unchecked")
	public T getItem(final String item) throws Exception {
		if (JCSGenericCacheManager.CACHE == null) {
			throw new Exception("JCS Cache not initialized");
		}
		final T t = (T) JCSGenericCacheManager.CACHE.get(item);
		return t;
	}

	/**
	 * Put item in JCS Cache.
	 */
	public boolean putItem(final String item, final T tObject) throws Exception {
		try {
			JCSGenericCacheManager.CACHE.put(item, tObject);
			return true;
		} catch (final Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Removes item from JCS Cache.
	 */
	public void removeItem(final String item) throws Exception {
		JCSGenericCacheManager.CACHE.remove(item);
	}
}