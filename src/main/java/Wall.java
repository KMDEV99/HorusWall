import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Wall implements Structure {
    private List<Block> blocks;

    public Wall() {
        this.blocks = new LinkedList<>();
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return blocks.stream()
                .filter(block -> block.getColor().equalsIgnoreCase(color))
                .findAny();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return blocks.stream()
                .filter(block -> block.getMaterial().equalsIgnoreCase(material))
                .collect(Collectors.toList());
    }

    @Override
    public int count() {
        int currentSize = 0;
        for (Block block : blocks) {
            if (block instanceof CompositeBlock) {
                currentSize += ((CompositeBlock) block).getBlocks().size();
            } else {
                currentSize++;
            }
        }
        return currentSize;
    }

    public void addBlocks(List<Block> blocksToAdd) {
        blocks.addAll(blocksToAdd);
    }
}
