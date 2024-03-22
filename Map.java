package javaHashMap;

interface Map<K, V> {
    void put(K key, V value);// Вставляет пару ключ-значение в HashMap.
    V get(K key); // Извлекает значение, связанное с указанным ключом.
    boolean containsKey(K key);// Проверяет, содержит ли HashMap указанный ключ.
    boolean containsValue(V value); // Проверяет, содержит ли HashMap указанное значение.
    void remove(K key); // Удаляет пару ключ-значение, связанную с указанным ключом.
    int size(); // Возвращает количество пар ключ-значение в HashMap.
}
