package metaheurystyczne;

import java.io.File;

public class Dane {
    public String type;
    public String edgeWeight;
    public String format;
    public int dimension;
    public File plik;

    public Dane(File plik) {
        this.plik = plik;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEdgeWeight(String edgeWeight) {
        this.edgeWeight = edgeWeight;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setDimension(String dimString) {
        this.dimension = Integer.parseInt(dimString);
    }
}
