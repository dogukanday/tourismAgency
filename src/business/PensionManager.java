package business;

import dao.HotelDao;
import dao.PensionDao;
import entities.Pension;

import java.util.ArrayList;

public class PensionManager {
    private final PensionDao pensionDao;
    private final HotelDao hotelDao;


    public PensionManager(){
        this.pensionDao = new PensionDao();
        this.hotelDao = new HotelDao();
    }

    public ArrayList<Pension> findAll(){
        return pensionDao.findAll();
    }

    public Pension getById(int id){
        return pensionDao.getById(id);
    }

    public boolean delete(int id){
        return pensionDao.delete(id);
    }

    public boolean save(Pension pension){
        if (pension.getId() == 0) {
            pensionDao.save(pension);
        }else {
            pensionDao.update(pension);
        }
        return true;
    }

    public boolean update(Pension pension){
        if (pension.getId() != 0) {
            pensionDao.update(pension);
        }
        return true;
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Pension> pensions){
        ArrayList<Object[]> pensionRow = new ArrayList<>();
        for (Pension pension : pensions){
            Object[] row = new Object[size];
            int i = 0;
            if(hotelDao.findByID(pension.getHotelId()).getHotelName() != null){
                row[i++] = pension.getId();
                row[i++] = pension.getHotelId();
                row[i++] = hotelDao.findByID(pension.getHotelId()).getHotelName();
                row[i++] = pension.getType();
                pensionRow.add(row);

            }


        }
        return pensionRow;
    }
}
