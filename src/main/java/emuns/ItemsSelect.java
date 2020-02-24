package emuns;

import lombok.Getter;

@Getter
public enum ItemsSelect {
    DEFAULT_VALUE("-- Select --");
    public String value;

    ItemsSelect(String var) {
        value = var;
    }

}
