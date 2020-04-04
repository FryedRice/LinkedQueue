/**
* @ Description: Java实现链式队列的数据结构：LinkedQueue<Item>
* @ Date: Feb.24th 2020
* @ Author: Jay Sonic
*/
import java.util.Iterator;

public class TestLinkedQueue {
    public static void main(String[] args) {
        LinkedQueue<Integer> test = new LinkedQueue<Integer>();
        
        System.out.printf("********入队测试开始.********\n");
        for(int i=0; i<20; ++i){
            test.push(i);
            System.out.printf("当前队列元素个数: %d.\n",test.size());
        }
        System.out.printf("********入队测试结束.********\n");
        

        System.out.printf("迭代器测试开始.\n");
        Iterator<Integer> it = test.iterator();
        while(it.hasNext()){
            System.out.printf("%d  ",it.next());
        }System.out.println("");
        System.out.printf("迭代器测试结束.\n");
        
        System.out.printf("********出队测试开始.********\n");
        int popedItem;
        for(int i=0; i<20; ++i){
            popedItem = test.pop();
            System.out.printf("元素%3d已出队.\t",popedItem);
            System.out.printf("当前队列元素个数: %d.\n",test.size());
        }
        System.out.printf("********出队测试结束.********\n");
    }
}

class LinkedQueue<Item> implements Iterable<Item> {
    private Node front; //队首指针
    private Node rear; //队尾指针
    private int N;
    private class Node{ //用内部类来定义节点
        Item item;
        Node next;
    }
    
    public boolean isEmpty(){
        return N == 0; //或者 first == null;
    }
    
    public int size(){
        return N;
    }
    
    public void push(Item val){ //入队
        Node oldRear = rear;
        rear = new Node();
        rear.item = val;
        rear.next = null;
        if(N==0) front = rear; //队空增加的操作
        else oldRear.next = rear;
        ++N;
    }
    
    public Item pop(){ //出队
        Item outcome = front.item;
        front = front.next;
        --N;
        if(N==0) rear = null; //队空增加的操作
        return outcome;
    }
    
    public Iterator<Item> iterator(){ //本方法用于实现Iterable<Item>接口
        return new LinkedQueueIterator(); //返回一个迭代器类ListIterator的实例
    }
    private class LinkedQueueIterator implements Iterator<Item>{ //迭代器类必须实现Iterator<Item>接口
        private Node current = front;
        public boolean hasNext(){
            return current != null;
        }
        public void remove() { }
        public Item next(){
            Item outcome = current.item;
            current = current.next;
            return outcome;
        }
    }
    
}