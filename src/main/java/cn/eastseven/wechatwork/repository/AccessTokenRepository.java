package cn.eastseven.wechatwork.repository;

import cn.eastseven.wechatwork.model.AccessTokenEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * @author eastseven
 */
public interface AccessTokenRepository extends CrudRepository<AccessTokenEntity, String> {
}
