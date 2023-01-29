import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WallTest {

    private Wall wall;
    private Block block;
    private Block secondBlock;
    private CompositeBlock compositeBlock;
    private Block innerBlock1;
    private Block innerBlock2;


    @BeforeAll
    public void setUp() {
        wall = new Wall();
        block = mock(Block.class);
        secondBlock = mock(Block.class);
        compositeBlock = mock(CompositeBlock.class);
        innerBlock1 = mock(Block.class);
        innerBlock2 = mock(Block.class);


        when(block.getColor()).thenReturn("red");
        when(block.getMaterial()).thenReturn("clay");

        when(secondBlock.getColor()).thenReturn("yellow");
        when(secondBlock.getMaterial()).thenReturn("stone");

        when(compositeBlock.getColor()).thenReturn("red");
        when(compositeBlock.getMaterial()).thenReturn("clay");
        when(compositeBlock.getBlocks()).thenReturn(List.of(innerBlock1, innerBlock2));

        wall.addBlocks(List.of(block, secondBlock, compositeBlock));
    }

    @Test
    public void findBlockByColor_exists() {
        assertEquals(secondBlock, wall.findBlockByColor("yellow").get());
    }

    @Test
    public void findBlockByColor_notExists() {
        assertThrows(NoSuchElementException.class, () -> {
            wall.findBlockByColor("color").get();
        });
    }

    @Test
    void findBlocksByMaterial_single() {
        assertEquals(1, wall.findBlocksByMaterial("stone").size());
    }

    @Test
    void findBlocksByMaterial_multiple() {
        assertEquals(2, wall.findBlocksByMaterial("clay").size());
    }

    @Test
    void count() {
        assertEquals(4, wall.count());
    }
}