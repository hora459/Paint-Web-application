package com.example.paint.controller;


import com.example.paint.Factory.Ifactory;
import com.example.paint.Factory.ShapeFactory;
import com.example.paint.Shapes.Shape;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

@CrossOrigin()
@RestController
@RequestMapping("/paint")
public class control {


    private Stack<Integer> undo = new Stack<>();
    private Stack<Integer> redo = new Stack<>();
    Stack<Shape> s=new Stack<>();

    //private Map<Integer, Pair<Stack<Shape>, Stack<Shape>>> objects = new HashMap<>();
    private Map<Integer,Object>cur=new HashMap<>();
    private Stack<Map<Integer,Object>> u_object=new Stack<>();
    private Stack<Map<Integer,Object>> r_object=new Stack<>();
    Ifactory factory=new ShapeFactory();
    @RequestMapping( value = "/add/{type}/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Stack<Map<Integer,Object>> addshape(@RequestBody String  inputData , @PathVariable String type, @PathVariable int id){
        JSONObject jsonObj = new JSONObject(inputData);
        Shape shape =factory.getshape(jsonObj,type);
        shape.settype(type);
        undo.push(id);
        Map<Integer,Object>new_state=new HashMap<>();
        cur.put(id,shape);
        new_state.putAll(cur);
        u_object.push(new_state);
        r_object.clear();
        redo.clear();
        System.out.println(jsonObj);
        shape.draw();
        return u_object;
    }
    @RequestMapping( value = "/modify/{type}/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Stack<Map<Integer,Object>> modifysape(@RequestBody String  inputData , @PathVariable String type, @PathVariable int id){
        System.out.println(id);
        org.json.JSONObject jsonObj = new JSONObject(inputData);
        Shape shape =factory.getshape(jsonObj,type);
        shape.settype(type);
        undo.push(id);
        Map<Integer,Object>mod= new HashMap<>();
        mod.putAll(u_object.peek());
        cur.replace(id,shape);
        mod.replace(id,shape);
        u_object.push(mod);
        r_object.clear();
        redo.clear();
        System.out.println(jsonObj);
        shape.draw();
        return u_object;
    }
    @RequestMapping( value = "/delete/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Stack<Map<Integer,Object>> delete(@PathVariable int id) throws NullPointerException,JSONException{
        if(undo.empty())
            return null;
        cur.remove(id);
        u_object.push(cur);
        r_object.clear();
        return u_object;
    }



    @RequestMapping( value = "/undo",method = RequestMethod.POST)
    @ResponseBody
    public Stack<Map<Integer,Object>> undo() throws NullPointerException,JSONException{
        if(undo.empty())
            return null;
        cur.remove(undo.peek());
        redo.push(undo.pop());
        r_object.push(u_object.pop());
        return u_object;
    }
    @RequestMapping( value = "/redo",method = RequestMethod.POST)
    @ResponseBody
    public Stack<Map<Integer,Object>> redo()throws NullPointerException,JSONException{
        if(redo.empty())
            return null;
        cur.put(redo.peek(),r_object.peek().get(redo.peek()));
        undo.push(redo.pop());
        u_object.push(r_object.pop());

        return u_object;
    }
    @RequestMapping( value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestBody String path )throws NullPointerException,JSONException{
        System.out.println(u_object.peek());
        JSONObject jsonObject = new JSONObject(u_object.peek());
        try {
            System.out.println(path);
            FileWriter file = new FileWriter(path);
            file.write(jsonObject.toString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("JSON file created: "+jsonObject);
        return new Result("Saved correctly",true);
    }
    @RequestMapping( value = "/load",method = RequestMethod.POST)
    @ResponseBody
    public Map<Integer,Object> load(@RequestBody String path )throws NullPointerException,JSONException{
        JSONParser parser = new JSONParser();
        ObjectMapper mapper = new ObjectMapper();
        Map<Integer,Object> userData = null;
        try {
            File fileObj = new File(path);
            if(fileObj==null) return null;
            userData = mapper.readValue(
                    fileObj, new TypeReference<Map<Integer,Object>>() {
                    });
            cur.putAll(userData);
            u_object.push(cur);
            System.out.println(userData);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return userData;
    }

    @RequestMapping(value = "/restart", method = RequestMethod.POST)
    @ResponseBody
    public Result restart() {
        u_object.clear();
        r_object.clear();
        undo.clear();
        redo.clear();
        return new Result("The app is restarted",true);
    }



}



