package qiniu.testfunc;

import java.util.ArrayList;
import java.util.List;

public class TaskConf {
    TaskConf(int index, boolean isleafnode){
        if (!isleafnode) {
            this.successors = new ArrayList<>();
        }

        this.index = index;
    }
    private int index;
    private List<TaskConf> successors;
    public List<TaskConf> getSuccessors() {
        return successors;
    }
    public void setSuccessors(List<TaskConf> successors) {
        this.successors = successors;
    }

}
