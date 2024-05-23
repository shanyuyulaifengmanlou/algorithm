package 优先队列;

import java.util.*;

class Node {
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int x;
    int y;
}

public class Main {
    static Comparator<Node> cNode = new Comparator<Node>() {
        public int compare(Node o1, Node o2) {
            if (o1.x != o2.x)
                return o1.x - o2.x;
            else
                return o2.y - o1.y;
        }
    };

    public static void main(String[] args) {
        Queue<Node> q = new PriorityQueue<>(cNode);
        Node n1 = new Node(1, 2);
        Node n2 = new Node(2, 5);
        Node n3 = new Node(2, 3);
        Node n4 = new Node(1, 2);
        q.add(n1);
        q.add(n2);
        q.add(n3);
        Node n;
        while (!q.isEmpty()) {
            n = q.poll();
            System.out.println("长: " + n.x + " 宽：" + n.y);
        }
    }
}
