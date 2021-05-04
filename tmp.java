public void validate() {
    if (this.taskDag.size() != 0) {
      Stack<TaskConf> nodestack = new Stack<>();
      Stack<Integer> indexstack = new Stack<>();
      Stack<Set<TaskConf>> fathernode = new Stack<>();
      TaskConf root = new TaskConf();
      root.setSuccessors(this.taskDag);
      TaskConf curnode = this.taskDag.get(0);
      while (curnode != root) {
        if (curnode.getSuccessors() != null) {
          curnode = curnode.getSuccessors().get(0);
          nodestack.push(curnode);
          indexstack.push(0);
        } else {
          indexstack.pop();
          nodestack.pop();
          while (nodestack.peek() != root) {
            nodestack.pop();
            int index = indexstack.pop();
            if (nodestack.peek().getSuccessors().size() - 1 > index) {
              curnode = nodestack.peek().getSuccessors().get(index + 1);
              indexstack.push(index + 1);
              break;
            }
          }
        }
      }
    }
  }
