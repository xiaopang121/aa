package com.insthub.cat.android.module2;

import com.insthub.cat.android.module.BaseData;

import java.io.Serializable;
import java.util.List;

/**
 * User:macbook
 * DATE:2017/12/20 20:39
 * Desc:${DESC}
 */

public class LabelData extends BaseData {


    /**
     * status : 10001
     * data : {"label_lv1":[{"level":1,"lable_name":"知识创业","label_lv2":[{"level":2,"lable_name":"BP规划","label_id":5,"parent_id":1},{"level":2,"lable_name":"融资服务","label_id":6,"parent_id":1},{"level":2,"lable_name":"营销课程","label_id":7,"parent_id":1},{"level":2,"lable_name":"职业提升","label_id":8,"parent_id":1},{"level":2,"lable_name":"行业课程","label_id":9,"parent_id":1},{"level":2,"lable_name":"管理咨询","label_id":10,"parent_id":1}],"label_id":1,"parent_id":0},{"level":1,"lable_name":"金融财税","label_lv2":[{"level":2,"lable_name":"记账工商","label_id":11,"parent_id":2},{"level":2,"lable_name":"税务策划","label_id":12,"parent_id":2},{"level":2,"lable_name":"知识产权","label_id":13,"parent_id":2},{"level":2,"lable_name":"会计评估","label_id":14,"parent_id":2},{"level":2,"lable_name":"资产管理","label_id":15,"parent_id":2},{"level":2,"lable_name":"融资服务","label_id":16,"parent_id":2},{"level":2,"lable_name":"企业保险","label_id":17,"parent_id":2},{"level":2,"lable_name":"金融科技","label_id":18,"parent_id":2}],"label_id":2,"parent_id":0},{"level":1,"lable_name":"营销策划","label_lv2":[{"level":2,"lable_name":"内容营销","label_id":19,"parent_id":3},{"level":2,"lable_name":"策划机构","label_id":20,"parent_id":3},{"level":2,"lable_name":"渠道资源","label_id":21,"parent_id":3},{"level":2,"lable_name":"营销工具","label_id":22,"parent_id":3},{"level":2,"lable_name":"商业智能","label_id":23,"parent_id":3}],"label_id":3,"parent_id":0},{"level":1,"lable_name":"数据IT","label_lv2":[{"level":2,"lable_name":"IT开发","label_id":24,"parent_id":4},{"level":2,"lable_name":"云通讯","label_id":25,"parent_id":4},{"level":2,"lable_name":"云存储","label_id":26,"parent_id":4},{"level":2,"lable_name":"行业方案","label_id":27,"parent_id":4},{"level":2,"lable_name":"云计算","label_id":28,"parent_id":4},{"level":2,"lable_name":"大数据","label_id":29,"parent_id":4},{"level":2,"lable_name":"数据应用","label_id":30,"parent_id":4},{"level":2,"lable_name":"数据安全","label_id":31,"parent_id":4}],"label_id":4,"parent_id":0}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<LabelLv1Bean> label_lv1;

        public List<LabelLv1Bean> getLabel_lv1() {
            return label_lv1;
        }

        public void setLabel_lv1(List<LabelLv1Bean> label_lv1) {
            this.label_lv1 = label_lv1;
        }

        public static class LabelLv1Bean  implements Serializable{
            /**
             * level : 1
             * lable_name : 知识创业
             * label_lv2 : [{"level":2,"lable_name":"BP规划","label_id":5,"parent_id":1},{"level":2,"lable_name":"融资服务","label_id":6,"parent_id":1},{"level":2,"lable_name":"营销课程","label_id":7,"parent_id":1},{"level":2,"lable_name":"职业提升","label_id":8,"parent_id":1},{"level":2,"lable_name":"行业课程","label_id":9,"parent_id":1},{"level":2,"lable_name":"管理咨询","label_id":10,"parent_id":1}]
             * label_id : 1
             * parent_id : 0
             */

            private int level;
            private String lable_name;
            private int label_id;
            private int parent_id;
            private List<LabelLv2Bean> label_lv2;

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getLable_name() {
                return lable_name;
            }

            public void setLable_name(String lable_name) {
                this.lable_name = lable_name;
            }

            public int getLabel_id() {
                return label_id;
            }

            public void setLabel_id(int label_id) {
                this.label_id = label_id;
            }

            public int getParent_id() {
                return parent_id;
            }

            public void setParent_id(int parent_id) {
                this.parent_id = parent_id;
            }

            public List<LabelLv2Bean> getLabel_lv2() {
                return label_lv2;
            }

            public void setLabel_lv2(List<LabelLv2Bean> label_lv2) {
                this.label_lv2 = label_lv2;
            }

            public static class LabelLv2Bean  implements Serializable{
                /**
                 * level : 2
                 * lable_name : BP规划
                 * label_id : 5
                 * parent_id : 1
                 */

                private int level;
                private String lable_name;
                private int label_id;
                private int parent_id;
                private boolean isSelect=false;

                public boolean isSelect() {
                    return isSelect;
                }

                public void setSelect(boolean select) {
                    isSelect = select;
                }

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public String getLable_name() {
                    return lable_name;
                }

                public void setLable_name(String lable_name) {
                    this.lable_name = lable_name;
                }

                public int getLabel_id() {
                    return label_id;
                }

                public void setLabel_id(int label_id) {
                    this.label_id = label_id;
                }

                public int getParent_id() {
                    return parent_id;
                }

                public void setParent_id(int parent_id) {
                    this.parent_id = parent_id;
                }
            }
        }
    }
}
