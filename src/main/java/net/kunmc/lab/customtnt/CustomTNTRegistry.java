package net.kunmc.lab.customtnt;

import java.util.*;

public class CustomTNTRegistry {
    private static final CustomTNTRegistry instance = new CustomTNTRegistry();
    private final Map<String, CustomTNT> customTNTMap = new HashMap<>();

    public static CustomTNTRegistry getInstance() {
        return instance;
    }

    private CustomTNTRegistry() {
    }

    public boolean register(CustomTNT tnt) {
        return customTNTMap.putIfAbsent(tnt.tabCompleteName(), tnt) == null;
    }

    public Optional<CustomTNT> getByName(String name) {
        return Optional.ofNullable(customTNTMap.get(name));
    }

    public Set<String> registeredNames() {
        return customTNTMap.keySet();
    }

    public List<CustomTNT> registeredTNTs() {
        return new ArrayList<>(customTNTMap.values());
    }
}
