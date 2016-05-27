package uk;

import com.googlecode.protobuf.format.JsonFormat;
import vector_tile.VectorTile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("0.pbf");
        System.out.println("Looking at: " + file.getAbsolutePath());

        manualHack(file);

        // Lets try JsonFormat
        byte[] array = Files.readAllBytes(file.toPath());
        VectorTile.Tile tile = VectorTile.Tile.parseFrom(array);
        JsonFormat jf = new JsonFormat();
        //jf.printToString(tile);
    }

    private static void manualHack(File file) throws IOException {
        byte[] array = Files.readAllBytes(file.toPath());
        VectorTile.Tile tile = VectorTile.Tile.parseFrom(array);

        System.out.println("Layer Count: " + tile.getLayersCount());
        List<VectorTile.Tile.Layer> layers = tile.getLayersList();
        for (VectorTile.Tile.Layer layer : layers) {
            System.out.println("\n\n");
            System.out.println("Layer Name: " + layer.getName());
            System.out.println("Features: " + layer.getFeaturesCount());
            System.out.println("Keys: " + layer.getKeysCount());
            System.out.println("Values: " + layer.getValuesCount());

            for (int i = 0; i < layer.getKeysCount(); i++) {
                String key = layer.getKeys(i);
                System.out.println("Key:" + key);
            }

            for (int i = 0; i < layer.getValuesCount(); i++) {
                VectorTile.Tile.Value value = layer.getValues(i);
                System.out.println("Value: " + value.getStringValue());
            }
            System.out.println("Version: " + layer.getVersion());


            for (int i = 0; i < layer.getFeaturesCount(); i++) {
                VectorTile.Tile.Feature feature = layer.getFeatures(i);
                System.out.println("feature: " + feature.getId());
                List<Integer> tags = feature.getTagsList();
                for (int j = 0; j < feature.getTagsCount(); j++) {

                }
                System.out.println("feature: " + feature.getTagsCount());
            }
        }
    }
}
