package cn.eastseven.wechatwork.model.msg;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author d7
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class TaskCardRequest extends BaseMsgRequest {

    @Override
    public void setMsgType(MsgType msgType) {
        super.setMsgType(MsgType.taskcard);
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.taskcard;
    }

    @JsonProperty("taskcard")
    private TaskCardBean taskCard;

    @Data
    @Builder
    public static class TaskCardBean {
        /**
         * title : 赵明登的礼物申请
         * description : 礼品：A31茶具套装<br>用途：赠与小黑科技张总经理
         * url : URL
         * task_id : taskid123
         * btn : [{"key":"key111","name":"批准","replace_name":"已批准","color":"red","is_bold":true},{"key":"key222","name":"驳回","replace_name":"已驳回"}]
         */

        private String title;
        private String description;
        private String url;
        @JsonProperty("task_id")
        private String taskId;
        private List<BtnBean> btn;

        @Data
        @Builder
        public static class BtnBean {
            /**
             * key : key111
             * name : 批准
             * replace_name : 已批准
             * color : red
             * is_bold : true
             */

            private String key;
            private String name;
        }
    }
}
