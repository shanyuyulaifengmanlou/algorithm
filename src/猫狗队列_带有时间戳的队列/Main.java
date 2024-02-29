package 猫狗队列_带有时间戳的队列;

import javax.swing.text.Element;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import static 猫狗队列_带有时间戳的队列.Pet.CatType;
import static 猫狗队列_带有时间戳的队列.Pet.DogType;

class Pet implements Cloneable {
    final static String CatType = "Cat";
    final static String DogType = "Dog";
    private String type;

    private int innerIndex;

    public Pet(String type) {
        this.type = type;
    }

    public String getPetType() {
        return this.type;
    }

    public void setIndex(int index) { // 运行时才设定其顺序
        this.innerIndex = index;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "type='" + type + '\'' +
                ", innerIndex=" + innerIndex +
                '}';
    }

    @Override
    public Pet clone() {
        try {
            // 这里调用Object的clone方法得到浅拷贝的结果
            Pet cloned = (Pet) super.clone();
            // 对于这个类里的所有字段值类型,浅拷贝跟深拷贝没有区别，
            // 因为字段是不可变的（final static字段和基本类型字段）
            // 如果存在可变类型的字段，需要单独为其实例化对象
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Can never happen
        }
    }

}

class Dog extends Pet {
    public Dog() {
        super(DogType);
    }
}

class Cat extends Pet {
    public Cat() {
        super(CatType);
    }
}

class PetElement {
    private Pet pet;
    private long order;

    public PetElement(Pet pet, long order) {
        this.pet = pet;
        this.order = order;
    }

    public long getOrder() {
        return this.order;
    }

    public String getPetTypeString() {
        return this.pet.getPetType();
    }

    public Pet Pet() {
        return this.pet;
    }
}

class CatDogQueue {
    private Queue<PetElement> catq;
    private Queue<PetElement> dogq;

    private ArrayList<Pet> outputRes;
    private long count;

    public CatDogQueue() {
        this.catq = new LinkedList<PetElement>();
        this.dogq = new LinkedList<PetElement>();
        this.outputRes = new ArrayList<Pet>();
    }


    // TODO：add 新增一个宠物无论种类
    public void add(Pet pet) {
        if (pet.getPetType().equals(CatType)) {
            this.catq.add(new PetElement(pet, this.count++));
        } else if (pet.getPetType().equals(DogType)) {
            this.dogq.add(new PetElement(pet, this.count++));
        } else {
            throw new RuntimeException("CatDogQueue: add pet error cause not corresponding type");
        }
    }

    // TODO: 题目说的其实是弹出所有的元素，因此需要加入  while 循环，而题目中的例题写的不太对
    public ArrayList<Pet> pollAll() {
        outputRes = new ArrayList<>();
        while (!this.catq.isEmpty() || !this.dogq.isEmpty()) {
            PetElement pet;
            if (!this.catq.isEmpty() && !this.dogq.isEmpty()) {
                pet = this.catq.peek().getOrder() < this.dogq.peek().getOrder() ? this.catq.poll() : this.dogq.poll();
            } else if (!this.catq.isEmpty()) {
                pet = this.catq.poll();
            } else {
                pet = this.dogq.poll();
            }
            outputRes.add(pet.Pet());
        }
        return outputRes;
    }

    public ArrayList<Pet> pollCat() {
        outputRes = new ArrayList<>();
        while (!this.catq.isEmpty()) {
            outputRes.add(this.catq.poll().Pet());
        }
        return outputRes;
    }

    public ArrayList<Pet> pollDog() {
        outputRes = new ArrayList<>();
        while (!this.dogq.isEmpty()) {
            outputRes.add(this.dogq.poll().Pet());
        }
        return outputRes;
    }

    public boolean isCatEmpty() {
        return this.catq.isEmpty();
    }

    public boolean isDogEmpty() {
        return this.dogq.isEmpty();
    }

    public boolean isPetEmpty() {
        return this.dogq.isEmpty() && this.catq.isEmpty();
    }
}


public class Main {


    public static void main(String[] args) {
        Pet[] pets = new Pet[]{new Dog(), new Cat(), new Dog(), new Dog(), new Cat(), new Dog(), new Cat()};
        int len = pets.length;
        Random rd = new Random();
        int testDataLen = 10;
        CatDogQueue queue = new CatDogQueue();
        Pet newPet;
        for (int i = 0; i < testDataLen; i++) {
            int num = rd.nextInt(len);
            newPet = pets[num].clone();
            newPet.setIndex(i); // 用于记录 入队的 顺序，检验算法正确与否
            queue.add(newPet);
        }
        ArrayList<Pet> outputPets = new ArrayList<>();
        outputPets = queue.pollCat();
        for (Pet element : outputPets) {
            System.out.println(element.toString());
        }
        System.out.println("is cat queue empty ? " + queue.isCatEmpty());
        System.out.println("is all queue empty ? " + queue.isPetEmpty());
        outputPets = queue.pollDog();
        for (Pet element : outputPets) {
            System.out.println(element.toString());
        }
        System.out.println("is all queue empty ? " + queue.isPetEmpty());

        // 再一次塞入数据
        for (int i = 0; i < testDataLen + 10; i++) {
            int num = rd.nextInt(len);
            newPet = pets[num].clone();
            newPet.setIndex(i);
            queue.add(newPet);
        }
        outputPets = queue.pollAll();
        for (Pet element : outputPets) {
            System.out.println(element.toString());
        }
        System.out.println("is cat queue empty ? " + queue.isCatEmpty());
        System.out.println("is dog queue empty ? " + queue.isDogEmpty());
        System.out.println("is all queue empty ? " + queue.isPetEmpty());
    }
}
