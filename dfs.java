package qiniu.testfunc;

//class Person{
//    private String name;
//    private int age;
//    public Person(String name1,int age1){
//        name = name1; 	 //this.name = name
//        age = age1;       //this.age = age
//    }
//    public void getPersoninfo(){
//        System.out.println("姓名为："+name+"年龄为："+this.age);
//    }
//}

public class dfs {

    public static void main(String[] args) {
        JobConf tmpconf = new JobConf();
        TaskConf taskc1 = new TaskConf(1, false);
        TaskConf taskc2 = new TaskConf(2, true);
        TaskConf taskc3 = new TaskConf(3, false);
        TaskConf taskc4 = new TaskConf(4, true);
        TaskConf taskc5 = new TaskConf(5, true);
//        TaskConf taskc5 = new TaskConf(5, false);
        TaskConf taskc6 = new TaskConf(6, false);
        TaskConf taskc7 = new TaskConf(7, false);

        tmpconf.getTaskDag().add(taskc1);
        tmpconf.getTaskDag().add(taskc7);
        taskc1.getSuccessors().add(taskc2);
        taskc1.getSuccessors().add(taskc3);
        taskc3.getSuccessors().add(taskc4);
        taskc3.getSuccessors().add(taskc5);
//        taskc5.getSuccessors().add(taskc6);
        taskc6.getSuccessors().add(taskc3);
        taskc7.getSuccessors().add(taskc6);

        tmpconf.validate();
    }


}
