package com.hzh.chapter10.binarytree;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/3/2 14:13
 */
public class BinaryTreeTest {

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode heroNode = new HeroNode(1, "宋江");

        HeroNode hero2 = new HeroNode(2, "卢俊义");
        HeroNode hero3 = new HeroNode(3, "索超");
        HeroNode hero4 = new HeroNode(4, "武松");
        HeroNode hero5 = new HeroNode(5, "林冲");
        HeroNode hero6 = new HeroNode(6, "宋江");
        HeroNode hero7 = new HeroNode(3, "吴用");
        HeroNode hero8 = new HeroNode(8, "花荣");
        HeroNode hero9 = new HeroNode(9, "鲁智深");

        heroNode.setLeft(hero2);
        heroNode.getLeft().setLeft(hero3);
        heroNode.getLeft().setRight(hero4);
        heroNode.setRight(hero5);
        heroNode.getRight().setLeft(hero6);
        heroNode.getRight().setRight(hero7);
        heroNode.getLeft().getLeft().setLeft(hero8);
        heroNode.getLeft().getLeft().setRight(hero9);

        binaryTree.setRoot(heroNode);

//        binaryTree.preOrder();
//        binaryTree.postOrder();
//        binaryTree.infixOrder();
/**
 *
 *              1
 *            2   5
 *           3 4 6 7
 *          8 9
 *
 *
 *
 *
 */
        // 中序遍历
        //HeroNode{no=8, name='花荣'}
        //HeroNode{no=3, name='索超'}
        //HeroNode{no=9, name='鲁智深'}
        //HeroNode{no=2, name='卢俊义'}
        //HeroNode{no=4, name='武松'}
        //HeroNode{no=1, name='宋江'}
        //HeroNode{no=6, name='宋江'}
        //HeroNode{no=5, name='林冲'}
        //HeroNode{no=7, name='吴用'}

//        binaryTree.deleteNode(3);
//        HeroNode heroNode1 = binaryTree.postOrderSearch(10);
        binaryTree.deleteNode(3);
        System.out.println(binaryTree);
    }

}
