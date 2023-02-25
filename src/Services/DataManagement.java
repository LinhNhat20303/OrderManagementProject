/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 
 */
public abstract class DataManagement<E> {

    protected List<E> entityList;
    private IDatabaseService databaseService; // su dung interface, khong new instance o class nay

    public void setDatabaseService(IDatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public DataManagement() {
        this.entityList = new ArrayList();
    }

    public void loadData() {
        List<String> entityStringList = databaseService.loadData();
        this.entityList.clear();
        E entity = null;
        for (String entityString : entityStringList) {
            entity = parseEntity(entityString);
            if (entity != null) {
                this.entityList.add(entity);
            }
        }
    }

    public boolean saveData() {
        List<String> entityList = new ArrayList();
        for (E entity : this.entityList) {
            entityList.add(entity.toString());
        }
        return databaseService.saveData(entityList);
    }

    public boolean insertData(E entity) {
        return this.databaseService.insert(entity.toString());
    }

    public boolean updateData(E entity) {
        return saveData();
    }

    public boolean deleteData(String id) {
        return saveData();
    }

    protected abstract E parseEntity(String stringEntity);
}
