/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras.binario;

import Estructuras.matriz.Matriz;

/**
 *
 * @author Xhunik_Local
 */
public class BinaryNode {
    public Matriz value;
    public BinaryNode left;
    public BinaryNode right;
    
    public BinaryNode(Matriz value){
        this.value = value;
        this.left = this.right = null;
    }
    
    public void insert(Matriz m){
        if (m.id < value.id){
            if (left == null)
                left = new BinaryNode(m);
            else
                left.insert(m);
        } else if (m.id > value.id){
            if (right == null)
                right = new BinaryNode(m);
            else
                right.insert(m);
        } 
        else {
            value = m;
        }
    }
    
    public Matriz search(int id){
        if (value.id == id){
            return this.value;
        }
        else {
            if (id < value.id){
                if (this.left == null)
                    return null;
                else
                    return this.left.search(id);
            }
            else if (id > value.id){
                if (this.right == null)
                    return null;
                else
                    return this.right.search(id);
            }
        }
        return null;
    }
    
    public String graph(){
        StringBuilder str = new StringBuilder();
        str.append("digraph G { rankdir=TB; node [shape = record, style=filled, fillcolor=seashell2];\n");
        str.append(exploreTree());
        str.append("}\n");
        return str.toString();
    }
    
    private String exploreTree(){
        StringBuilder str = new StringBuilder();
        if (left == null && right == null)
            str.append("node").append(value.id).append(" [ label =\"").append(value.id).append("\"];\n");
        else
            str.append("node").append(value.id).append(" [ label =\"<C0>|").append(value.id).append("|<C1>\"];\n");
        
        if (left != null){
            str.append(left.exploreTree()).append("node").append(value.id)
                    .append(":C0->node").append(left.value.id).append(";\n");
        }
        
        if (right != null){
            str.append(right.exploreTree()).append("node").append(value.id)
                    .append(":C1->node").append(right.value.id).append(";\n");
        }
        
        return str.toString();
    }
    
}
