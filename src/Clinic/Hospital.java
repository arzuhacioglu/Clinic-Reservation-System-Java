package Clinic;

import java.io.Serializable;
import java.util.LinkedList;

public class Hospital implements Serializable {
    private final int id;
    private String name;
    private LinkedList<Section> sections;

    public Hospital(int id, String name) {
        this.id = id;
        this.name = name;
        this.sections = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Section getSections(int id) {
        for (Section section : sections) {
            if (section.getId() == id) {
                return section;
            }
        }
        return null;
    }

    public Section getSections(String name) {
        for (Section section : sections) {
            if (section.getName().equals(name)) {
                return section;
            }
        }
        return null;
    }

    public LinkedList<Section> getSection() {
        return sections;
    }

    public void addSection(Section section) {
        if(section!=null){
            if(!sections.contains(section)){
                sections.add(section);
            }else{
                System.out.println("Section already exists");
            }
        }else{
            System.out.println("Section is null");
        }
    }

    public void removeSection(Section section) {
        if(section!=null){
            for(Section s:sections){
                if(s.getId()==section.getId()){
                    sections.remove(s);
                }
            }
        }else{
            System.out.println("Section is null");
        }
    }

    public void listSections() {
        if(sections!=null){
            for(Section s:sections){
                System.out.println(s);
            }
        }
    }

}
