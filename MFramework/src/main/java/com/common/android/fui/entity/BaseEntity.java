package com.common.android.fui.entity;

import java.io.Serializable;
import java.util.Map;

import rx.Observable;

/**
 * '
 */
public interface BaseEntity {
    class BaseBean implements Serializable {
        public long id;
        public String objectId;
        public Map<String, String> param;
    }

    interface IListBean {
        Observable getPageAt(int page);

        void setParam(Map<String, String> param);
    }

    abstract class ListBean extends BaseBean implements IListBean {
        @Override
        public void setParam(Map<String, String> param) {
            this.param=param;
        }
    }
}
