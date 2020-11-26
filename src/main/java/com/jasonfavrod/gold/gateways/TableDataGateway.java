package com.jasonfavrod.gold.gateways;

import java.util.List;

public interface TableDataGateway<T> {
    /** @returns The number of rows deleted. */
    public int delete(int id);
    /** @returns The number of rows deleted. */
    public int delete(T entity);
    public List<T> find();
    /** @returns True if insert successful, false otherwise. */
    public boolean insert(T entity);
    /** @returns The number of rows updated. */
    public int update(T entity);
}
