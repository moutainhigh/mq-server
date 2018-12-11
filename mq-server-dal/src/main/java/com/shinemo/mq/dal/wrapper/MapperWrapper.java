package com.shinemo.mq.dal.wrapper;



import com.shinemo.mq.client.common.entity.BaseDO;
import com.shinemo.mq.client.common.entity.BaseQuery;
import com.shinemo.mq.client.common.error.CommonError;
import com.shinemo.mq.client.common.error.ErrorWrapper;
import com.shinemo.mq.client.common.exception.DatabaseSqlExecuteException;
import com.shinemo.mq.client.common.exception.ParamterInvalidException;
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
			throw new ParamterInvalidException(CommonError.MAPPER_NULL_COUNT);
		}
		if (query == null) {
			throw new ParamterInvalidException(CommonError.QUERY_NULL_COUNT);
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
			throw new ParamterInvalidException(CommonError.MAPPER_NULL_COUNT);
		}
		if (query == null) {
			throw new ParamterInvalidException(CommonError.QUERY_NULL_COUNT);
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

	public static  <Q extends BaseQuery, D extends BaseDO>  Result<D> get(BaseMapper<Q, D> mapper, Q query) {
		return get(mapper, query, null);
	}

	public static  <Q extends BaseQuery, D extends BaseDO> Result<D> get(BaseMapper<Q, D>  mapper, Q query, ErrorWrapper error) {
		if (mapper == null) {
			throw new ParamterInvalidException(CommonError.MAPPER_NULL_COUNT);
		}
		if (query == null) {
			throw new ParamterInvalidException(CommonError.QUERY_NULL_COUNT);
		}
		try {
			D d = mapper.get(query);
			return ResultFactory.success(d, error);
		} catch (Throwable e) {
			throw new DatabaseSqlExecuteException(error == null ? CommonError.SQL_ERROR_FINND : error, e);
		}
	}

	public static <Q extends BaseQuery, D extends BaseDO> Result<D> insert(BaseMapper<Q, D>  mapper, D entity) {
		return insert(mapper, entity, null);
	}

	public static <Q extends BaseQuery, D extends BaseDO>Result<D> insert(BaseMapper<Q, D> mapper,D  entity, ErrorWrapper error) {
		if (mapper == null) {
			throw new ParamterInvalidException(CommonError.MAPPER_NULL_COUNT);
		}
		if (entity == null) {
			throw new ParamterInvalidException(CommonError.ENTTY_NULL);
		}
		try {
			mapper.insert(entity);
			return ResultFactory.success(entity);
		} catch (Throwable e) {
			throw new DatabaseSqlExecuteException(error == null ? CommonError.SQL_ERROR_INSERT : error, e);
		}
	}

	public static  <Q extends BaseQuery, D extends BaseDO> Result<D> update(BaseMapper<Q, D> mapper, D entity) {
		return update(mapper, entity, null);
	}

	public static  <Q extends BaseQuery, D extends BaseDO> Result<D>  update(BaseMapper<Q, D> mapper, D entity, ErrorWrapper error) {
		if (mapper == null) {
			throw new ParamterInvalidException(CommonError.MAPPER_NULL_COUNT);
		}
		if (entity == null) {
			throw new ParamterInvalidException(CommonError.ENTTY_NULL);
		}
		try {
			long result = mapper.update(entity);
			if (result < 1) {
				if (error == null) {
					return ResultFactory.success(entity);
				}
				return ResultFactory.success(error);
			}
			return ResultFactory.success(entity);
		} catch (Throwable e) {
			throw new DatabaseSqlExecuteException(error == null ? CommonError.SQL_ERROR_UPDATE : error, e);
		}
	}
}
