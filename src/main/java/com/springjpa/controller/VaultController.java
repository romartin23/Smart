package com.springjpa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springjpa.tree.model.Leaf;
import com.springjpa.tree.model.Node;
import com.springjpa.tree.model.Parent;
import com.stratio.datacentric.smart.vault.knowledge.concepts.Secret;
import com.stratio.datacentric.smart.vault.knowledge.concepts.VaultElement;
import com.stratio.datacentric.smart.vault.knowledge.concepts.Volume;
import com.stratio.datacentric.smart.vault.knowledge.service.VaultKnowledgeService;

@RestController
public class VaultController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private VaultKnowledgeService vaultKnowledgeService;
    
    public VaultController() {
    	try {
    		vaultKnowledgeService = new VaultKnowledgeService();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	}

    @RequestMapping("/vault")
    public List<Node> vault(@RequestParam(value="name", defaultValue="World") String name) {
    	List<Node> nodes = new ArrayList<Node>();
    	try {
    		 List<Volume>  volumes = vaultKnowledgeService.getVaultKnowledge();
    		 nodes= toTree(volumes);
    	} catch (Exception e) {
    		e.printStackTrace();
		}
    	return nodes;
    }
    
    private List<Node> toTree(List<Volume>  volumes) {
    	List<Node> tree = 
    			volumes.stream().map((x)->{
	    		return toNode(x);
	    	}).collect(Collectors.toList());
    	
    	return tree;
    }
    
    private Node toNode(VaultElement vaultElement) {
		Node node;
    	if (vaultElement instanceof Secret) {
    		Secret secret = (Secret) vaultElement;
    		node = new Leaf();
    		node.setTitle(secret.getName());
    		node.setKey(secret.getId());
    	} else {
    		Volume volume = (Volume) vaultElement;
    		node = new Parent();
    		
    		Parent parent = (Parent) node;
    		
    		List<Node> children = new ArrayList<Node>();
    		
    		node.setTitle(volume.getName());
    		node.setKey(volume.getId());
    		
    		List<VaultElement> vaultElements = volume.getChildren();
    		if (vaultElements != null) {
	    		for (int cont=0;cont<vaultElements.size();cont++) {
	    			children.add(toNode(vaultElements.get(cont)));
	    		}
    		}
    		
    		parent.setChildren(children);
    		
    	}
    	return node;
    }
    
}