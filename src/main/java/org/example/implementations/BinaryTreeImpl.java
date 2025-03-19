package org.example.implementations;

import org.example.annotations.JsonElement;
import org.example.annotations.JsonSerializable;
import org.example.interfaces.BinaryTree;
import org.example.interfaces.Processor;

import java.util.ArrayList;
import java.util.List;

@JsonSerializable
public class BinaryTreeImpl<T> implements BinaryTree<T> {

    @JsonElement(key = "root")
    private BinaryTreeNode<T> root;
    private int size;

    public BinaryTreeImpl() {
        root = null;
        size = 0;
    }

    public void insert(T key) {
        root = insertRec(root, key);
        size++;
    }

    private BinaryTreeNode<T> insertRec(BinaryTreeNode<T> root, T key) {
        if (root == null) {
            return new BinaryTreeNode<>(key);
        }
        int cmp = Integer.compare(key.hashCode(), root.key.hashCode());
        if (cmp < 0) {
            root.left = insertRec(root.left, key);
        } else if (cmp > 0) {
            root.right = insertRec(root.right, key);
        }
        return root;
    }

    public T search(T key) {
        return searchRec(root, key);
    }

    private T searchRec(BinaryTreeNode<T> root, T key) {
        if (root == null) {
            return null;
        }
        if (key.equals(root.key)) {
            return root.key;
        }
        int cmp = Integer.compare(key.hashCode(), root.key.hashCode());
        return cmp < 0 ? searchRec(root.left, key) : searchRec(root.right, key);
    }

    public void delete(T key) {
        root = deleteRec(root, key);
        size--;
    }

    private BinaryTreeNode<T> deleteRec(BinaryTreeNode<T> root, T key) {
        if (root == null) {
            return null;
        }
        int cmp = Integer.compare(key.hashCode(), root.key.hashCode());
        if (cmp < 0) {
            root.left = deleteRec(root.left, key);
        } else if (cmp > 0) {
            root.right = deleteRec(root.right, key);
        } else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            root.key = minValue(root.right);
            root.right = deleteRec(root.right, root.key);
        }
        return root;
    }

    private T minValue(BinaryTreeNode<T> root) {
        T minVal = root.key;
        while (root.left != null) {
            minVal = root.left.key;
            root = root.left;
        }
        return minVal;
    }

    @Override
    public void print() {
        printTreeRec(root, "", "Root");
    }

    private void printTreeRec(BinaryTreeNode<T> node, String prefix, String position) {
        if (node != null) {
            System.out.println(prefix + position + ": " + node.key);
            printTreeRec(node.left, prefix + "    ", "L");
            printTreeRec(node.right, prefix + "    ", "R");
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public BinaryTree<T> processElements(Processor<T> processor) {
        var newTree = new BinaryTreeImpl<T>();
        processTreeRec(root, processor, newTree);
        return newTree;
    }

    private void processTreeRec(BinaryTreeNode<T> node, Processor<T> processor, BinaryTree<T> newTree) {
        if (node != null) {
            T result = processor.process(node.key);
            newTree.insert(result);
            processTreeRec(node.left, processor, newTree);
            processTreeRec(node.right, processor, newTree);
        }
    }

    @Override
    public List<T> getElements() {
        List<T> elements = new ArrayList<>();
        inOrderTraversal(root, elements);
        return elements;
    }

    private void inOrderTraversal(BinaryTreeNode<T> node, List<T> elements) {
        if (node != null) {
            inOrderTraversal(node.left, elements);
            elements.add(node.key);
            inOrderTraversal(node.right, elements);
        }
    }
}

