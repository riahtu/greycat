package org.mwg.memory.offheap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.mwg.core.chunk.AbstractTimeTreeTest;
import org.mwg.utility.Unsafe;

public class OffHeapTimeTreeChunkTest extends AbstractTimeTreeTest {

    public OffHeapTimeTreeChunkTest() {
        super(new OffHeapMemoryFactory());
    }

    @Before
    public void setUp() throws Exception {
        OffHeapByteArray.alloc_counter = 0;
        OffHeapDoubleArray.alloc_counter = 0;
        OffHeapLongArray.alloc_counter = 0;
        OffHeapStringArray.alloc_counter = 0;
        Unsafe.DEBUG_MODE = true;
    }

    @After
    public void tearDown() throws Exception {
        Assert.assertEquals(OffHeapByteArray.alloc_counter, 0);
        Assert.assertEquals(OffHeapDoubleArray.alloc_counter, 0);
        Assert.assertEquals(OffHeapLongArray.alloc_counter, 0);
        Assert.assertEquals(OffHeapStringArray.alloc_counter, 0);
    }

}