package org.andrew.commons.cache.test;


import org.andrew.commons.cache.api.Cache;
import org.andrew.commons.cache.impl.exception.CacheException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Configurable
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/common-cachebean.xml")
public class CacheTest {

    @Autowired
    Cache cache;

    @Before
    public void setUp() throws Exception {
        cache.init("MKCache");
        cache.clear();
    }

    @Test
    public void testGetString() throws CacheException {
        cache.put("aa", "123");
        Assert.assertEquals("123", cache.get("aa"));
    }

    @Test
    public void testPutSafe() {
        try {
            cache.putSafe("aa", 123);
            cache.putSafe("aa", "bb");
            Assert.fail();
        } catch (CacheException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testPutStringStringObject() throws CacheException {
        cache.put("group", "aa", "123");
        Assert.assertEquals("123", cache.get("group", "aa"));
    }

    @Test
    public void testGetGroupKeys() throws CacheException {
        cache.put("groupa", "aa1", "123");
        cache.put("groupa", "aa2", "123");
        cache.put("groupa", "aa3", "123");
        Assert.assertEquals(3, cache.getGroupKeys("groupa").size());
    }

    @Test
    public void testCleanGroup() throws CacheException {
        cache.put("bb", "123");
        cache.put("group", "aa1", "123");
        cache.put("group", "aa2", "123");
        cache.put("group", "aa3", "123");
        cache.cleanGroup("group");
        cache.get("group", "aa1");
    }

    @Test
    public void testClear() throws CacheException {
        cache.put("bb", "123");
        Assert.assertEquals("123", cache.get("bb"));
        cache.clear();
        cache.get("bb");
    }

    @Test
    public void testRemoveStringString() throws CacheException {
        cache.put("group", "bb", "123");
        Assert.assertEquals("123", cache.get("group", "bb"));
        cache.remove("group", "bb");
        cache.get("group", "bb");
    }

    @Test
    public void testRemove() throws CacheException {
        cache.put("bb", "123");
        Assert.assertEquals("123", cache.get("bb"));
        cache.remove("bb");
        cache.get("bb");
    }

    @Test
    public void testGetStats() {
        System.out.println(System.getProperty("user.dir"));
        System.out.println(cache.getStats());
    }

    @Test
    public void testFreeMemoryElements() throws CacheException {
        cache.put("aa", "aa");
        for (int i = 0; i < 100; i++) {
            cache.put("aa" + i, i);
        }
        for (int j = 0; j < 500; j++) {
            cache.get("aa");
        }
        try {
            cache.freeMemoryElements(100);
            Assert.assertEquals("aa", cache.get("aa"));
            cache.get("aa1");
            Assert.fail();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Test
    public void testGetStrings() throws CacheException {
        cache.put("aa", "123");
        cache.put("bb", "456");
        Assert.assertEquals(2, cache.get(new String[]{"aa", "bb"}).length);
    }

    @Test
    public void testGetGroupStrings() throws CacheException {
        cache.put("group", "aa", "111");
        cache.put("group", "bb", "222");
        cache.put("group1", "cc", "333");
        cache.put("group1", "dd", "444");
        Assert.assertEquals(2, cache.get("group", new String[]{"aa", "bb"}).length);
        Assert.assertEquals(2, cache.get("group1", new String[]{"cc", "dd"}).length);
    }

    @Test
    public void testRemoveStrings() throws CacheException {
        cache.put("aa", "111");
        cache.put("bb", "222");
        cache.remove(new String[]{"aa", "bb"});
        Assert.assertNull(cache.get(new String[]{"aa", "bb"})[0]);
        Assert.assertNull(cache.get(new String[]{"aa", "bb"})[1]);
    }

    @Test
    public void testRemoveGroupStrings() throws CacheException {
        cache.put("group", "aa", "111");
        cache.put("group", "bb", "222");
        cache.put("group1", "cc", "333");
        cache.put("group1", "dd", "444");
        cache.remove("group", new String[]{"aa", "bb"});
        Assert.assertNull(cache.get("group", new String[]{"aa", "bb"})[0]);
        Assert.assertNull(cache.get("group", new String[]{"aa", "bb"})[1]);
    }
}
