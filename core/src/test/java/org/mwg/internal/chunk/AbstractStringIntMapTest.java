package org.mwg.internal.chunk;

import org.junit.Assert;
import org.junit.Test;
import org.mwg.Type;
import org.mwg.chunk.ChunkSpace;
import org.mwg.chunk.ChunkType;
import org.mwg.chunk.StateChunk;
import org.mwg.plugin.MemoryFactory;
import org.mwg.struct.StringIntMap;
import org.mwg.utility.HashHelper;

public abstract class AbstractStringIntMapTest {

    private MemoryFactory factory;

    public AbstractStringIntMapTest(MemoryFactory factory) {
        this.factory = factory;
    }

    @Test
    public void genericTest() {

        ChunkSpace space = factory.newSpace(100, null, false);
        StateChunk chunk = (StateChunk) space.createAndMark(ChunkType.STATE_CHUNK, 0, 0, 0);
        StringIntMap map = (StringIntMap) chunk.getOrCreate(0, Type.STRING_TO_INT_MAP);
        map.put("Hello", 0);
        Assert.assertTrue(0 == map.getValue("Hello"));

        map.put("Hello1", 1);
        Assert.assertTrue(0 == map.getValue("Hello"));
        Assert.assertTrue(1 == map.getValue("Hello1"));

        //no effect
        map.put("Hello1", 1);
        map.put("Hello", 1);
        map.put("Hello1", 2);
        Assert.assertTrue(1 == map.getValue("Hello"));
        Assert.assertTrue(2 == map.getValue("Hello1"));

        map.put("DictionaryUsage", 10);
        Assert.assertTrue(10 == map.getValue("DictionaryUsage"));

        Assert.assertEquals(1, map.getValue("Hello"));
        Assert.assertTrue(HashHelper.equals("Hello", map.getByHash(HashHelper.hash("Hello"))));

        /*


        final String[] keys = new String[3];
        final long[] values = new long[3];
        final int[] resIndex = {0};
        map.each(new StringLongMapCallBack() {
            @Override
            public void on(final String key, final long value) {
                keys[resIndex[0]] = key;
                values[resIndex[0]] = value;
                resIndex[0]++;
            }
        });
        Assert.assertTrue(1 == values[0]);
        Assert.assertTrue(2 == values[1]);
        Assert.assertTrue(Constants.NULL_LONG == values[2]);
        Assert.assertTrue(HashHelper.equals("Hello", keys[0]));
        Assert.assertTrue(HashHelper.equals("Hello1", keys[1]));
        Assert.assertTrue(HashHelper.equals("DictionaryUsage", keys[2]));

        //force the graph to do a rehash capacity
        for (int i = 0; i < CoreConstants.MAP_INITIAL_CAPACITY; i++) {
            map.put("i_" + i, i);
        }
        //test that all values are consistent
        for (int i = 0; i < CoreConstants.MAP_INITIAL_CAPACITY; i++) {
            Assert.assertTrue(map.getValue("i_" + i) == i);
        }
        */

        space.free(chunk);
        space.freeAll();

    }

}
