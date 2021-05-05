package qiniu.testfunc;

import java.util.*;

public class JobConf {
    JobConf(){
        this.taskDag = new ArrayList<>();
    }
    private List<TaskConf> taskDag;
    public List<TaskConf> getTaskDag() {
        return taskDag;
    }
    public void setTaskDag(List<TaskConf> taskDag) {
        this.taskDag = taskDag;
    }
    public void validate() {
        if (this.taskDag.size() != 0) {
            Stack<TaskConf> nodestack = new Stack<>();
            Stack<Integer> indexstack = new Stack<>();
            Set<TaskConf> linknodes = new HashSet<>();
            TaskConf root = new TaskConf(0,false);
            nodestack.push(root);
            indexstack.push(0);
            root.setSuccessors(this.taskDag);
            TaskConf curnode = this.taskDag.get(0);
            nodestack.push(curnode);
            linknodes.add(curnode);
            indexstack.push(0);
            while (curnode != root) {
                if (curnode.getSuccessors() != null) {
                    curnode = curnode.getSuccessors().get(0);
                    nodestack.push(curnode);
                    if (linknodes.contains(curnode)) {
//                      throw new EmptyThreadPoolNameException();
                        System.err.println("error!! this is circle!");
                        return;
                    }
                    linknodes.add(curnode);
                    indexstack.push(0);
                } else {
                    linknodes.remove(nodestack.pop());
                    while (curnode != root) {
                        int index = indexstack.peek();
                        if (nodestack.peek().getSuccessors().size() - 1 > index) {
                            curnode = nodestack.peek().getSuccessors().get(index + 1);
                            nodestack.push(curnode);
                            if (linknodes.contains(curnode)) {
//                              throw new EmptyThreadPoolNameException();
                                System.err.println("error!! this is circle!");
                                return;
                            }
                            linknodes.add(curnode);
                            indexstack.pop();
                            indexstack.push(index + 1);
                            break;
                        } else {
                            indexstack.pop();
                            curnode = nodestack.pop();
                            linknodes.remove(curnode);
                        }
                    }
                }
            }
        }
    }
}
