package com.shinemo.mq.dal.wrapper;



import com.shinemo.mq.client.common.entity.BaseDO;
import com.shinemo.mq.client.common.entity.BaseQuery;
import com.shinemo.mq.client.common.error.CommonError;
import com.shinemo.mq.client.common.error.ErrorWrapper;
import com.shinemo.mq.client.common.exception.DatabaseSqlExecuteException;
import com.shinemo.mq.client.common.list.ListWrapper;
import com.shinemo.mq.client.common.result.Result;
import com.shinemo.mq.client.common.result.ResultFactory;
import com.shinemo.mq.dal.mapper.BaseMapper;


import java.util.Collections;
import java.util.List;



/**
 * Created by harold on 03/08/2017.
 */
public class MapperWrapper {

	public static <Q extends BaseQuery, D extends BaseDO> Result<Long> count(BaseMapper<Q, D> mapper, Q query) {
		return count(mapper, query, null);
	}

	public static <Q extends BaseQuery, D extends BaseDO> Result<Long> count(BaseMapper<Q, D> mapper, Q query, ErrorWrapper error) {
		if (mapper == null) {
			throw new DatabaseSqlExecuteException(CommonError.MAPPER_NULL_COUNT);
		}
		if (query == null) {
			throw new DatabaseSqlExecuteException(CommonError.QUERY_NULL_COUNT);
		}
		try {
			return ResultFactory.success(mapper.count(query));
		} catch (Throwable e) {
			throw new DatabaseSqlExecuteException(error == null ? CommonError.SQL_ERROR_COUNT : error, e);
		}
	}

	public static <Q extends BaseQuery, D extends BaseDO> Result<ListWrapper<D>> find(BaseMapper<Q, D> mapper, Q query) {
		return find(mapper, query, null);
	}

	public static  <Q extends BaseQuery, D extends BaseDO> Result<ListWrapper<D>> find(BaseMapper<Q, D> mapper, Q query, ErrorWrapper error) {
		if (mapper == null) {
			throw new DatabaseSqlExecuteException(CommonError.MAPPER_NULL_COUNT);
		}
		if (query == null) {
			throw new DatabaseSqlExecuteException(CommonError.QUERY_NULL_COUNT);
		}
		try {
			if (query.isPageEnable()) {
				long count = mapper.count(query);
				if (count < 1) {
					return ResultFactory.success(new ListWrapper(Collections.emptyList(), 0L, query.getCurrentPage(), query.getPageSize()));
				}
				if (query.getStartRow().longValue() >= count) {
					return ResultFactory.success(new ListWrapper(Collections.emptyList(), count, query.getCurrentPage(), query.getPageSize()));
				}
				query.putTotalItem(count);
				return ResultFactory.success(new ListWrapper(mapper.find(query), query.getTotalItem(), query.getCurrentPage(), query.getPageSize()));
			}
			List<D> list = mapper.find(query);
			return ResultFactory.success(new ListWrapper(list, Long.valueOf(list.size()), 1L, Long.valueOf(list.size())));
		} catch (Throwable e) {
			throw new DatabaseSqlExecuteException(error == null ? CommonError.SQL_ERROR_FINND : error, e);
		}
	}

	public static <Q extends Query, D extends Domain, V extends View> Result<List<V>> findByNoPage(ViewMapper<Q, D, V> mapper, Q query) {
		return findByNoPage(mapper, query, null);
	}

	public static <Q extends Query, D extends Domain, V extends View> Result<List<V>> findByNoPage(ViewMapper<Q, D, V> mapper, Q query, ErrorInfo error) {
		if (mapper == null) {
			throw new BizException(MAPPER_NULL_FIND);
		}
		if (query == null) {
			throw new BizException(QUERY_NULL_FIND);
		}
		try {
			if(query.isPageEnable()) {
				return Result.error(NO_PAGE_FIND);
			}
			List<V> list = mapper.find(query);
			return Result.success(list);
		} catch (Throwable e) {
			throw new BizException(error == null ? SQL_ERROR_FIND : error, e);
		}
	}

	public static <Q extends Query, D extends Domain, V extends View> Result<V> get(ViewMapper<Q, D, V> mapper, Q query) {
		return get(mapper, query, null);
	}

	public static <Q extends Query, D extends Domain, V extends View> Result<V> get(ViewMapper<Q, D, V> mapper, Q query, ErrorInfo error) {
		if (mapper == null) {
			throw new BizException(MAPPER_NULL_GET);
		}
		if (query == null) {
			throw new BizException(QUERY_NULL_GET);
		}
		try {
			V d = mapper.get(query);
			return Result.success(d, error);
		} catch (Throwable e) {
			throw new BizException(error == null ? SQL_ERROR_GET : error, e);
		}
	}

	public static <Q extends Query, D extends Domain, V extends View> Result<D> insert(ViewMapper<Q, D, V> mapper, D entity) {
		return insert(mapper, entity, null);
	}

	public static <Q extends Query, D extends Domain, V extends View> Result<D> insert(ViewMapper<Q, D, V> mapper, D entity, ErrorInfo error) {
		if (mapper == null) {
			throw new BizException(MAPPER_NULL_INSERT);
		}
		if (entity == null) {
			throw new BizException(ENTITY_NULL_INSERT);
		}
		try {
			mapper.insert(entity);
			return Result.success(entity);
		} catch (Throwable e) {
			throw new BizException(error == null ? SQL_ERROR_INSERT : error, e);
		}
	}

	public static <Q extends Query, D extends Domain, V extends View> Result<D> update(ViewMapper<Q, D, V> mapper, D entity) {
		return update(mapper, entity, null);
	}

	public static <Q extends Query, D extends Domain, V extends View> Result<D> update(ViewMapper<Q, D, V> mapper, D entity, ErrorInfo error) {
		if (mapper == null) {
			throw new BizException(MAPPER_NULL_UPDATE);
		}
		if (entity == null) {
			throw new BizException(ENTITY_NULL_UPDATE);
		}
		try {
			long result = mapper.update(entity);
			if (result < 1) {
				if (error == null) {
					return Result.success(entity);
				}
				return Result.success(error);
			}
			return Result.success(entity);
		} catch (Throwable e) {
			throw new BizException(error == null ? SQL_ERROR_UPDATE : error, e);
		}
	}
}
