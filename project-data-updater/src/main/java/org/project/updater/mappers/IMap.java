package org.project.updater.mappers;

public interface IMap<TDto, TEntity> { TEntity toEntity(TDto dto); }
