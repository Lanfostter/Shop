package com.example.shop.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.shop.dto.UserDTO;
import com.example.shop.entity.UserEntity;
import com.example.shop.repository.UserRepository;
import com.example.shop.service.UserServiceDAO;

@Service
public class UserServiceImpl implements UserServiceDAO {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserRepository userRepository;

    @Override
    public void create(UserDTO userDTO) {
        userRepository.save(convertToEntity(userDTO));
    }

    @Override
    public void update(UserDTO userDTO) {
        userRepository.save(convertToEntity(userDTO));
    }

    @Override
    public boolean delete(int id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<UserDTO> getListUser() {
        List<UserDTO> list = new ArrayList<UserDTO>();
        for (UserEntity userEntity : userRepository.findAll()) {
            UserDTO userDTO = convertToDTO(userEntity);
            list.add(userDTO);
        }
        return list;
    }

    @Override
    public List<UserDTO> findbyName(String name) {
        List<UserDTO> list = new ArrayList<UserDTO>();
        for (UserEntity userEntity : userRepository.findByName("%" + name + "%")) {
            list.add(convertToDTO(userEntity));
        }
        return list;
    }

    public UserDTO convertToDTO(UserEntity userEntity) {
        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
        return userDTO;
    }

    public UserEntity convertToEntity(UserDTO userDTO) {
        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        return userEntity;
    }

    @Override
    public UserDTO findbyId(int id) {
        return convertToDTO(userRepository.findById(id).get());
    }

    @Override
    public void importToDB(List<MultipartFile> files) {
        if (!files.isEmpty()) {
            List<UserDTO> userDTOs = new ArrayList<UserDTO>();
            for (MultipartFile file : files) {
                try {
                    XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
                    XSSFSheet sheet = workbook.getSheetAt(0);
                    for (int rowIndex = 0; rowIndex < getNumberOfNonEmptyCells(sheet, 0) - 1; rowIndex++) {
                        XSSFRow row = sheet.getRow(rowIndex);
                        if (rowIndex == 0) {
                            continue;
                        }
                        int id = Integer.parseInt(getValue(row.getCell(0)).toString());
                        String name = String.valueOf(row.getCell(2));
                        String email = String.valueOf(row.getCell(3));
                        String username = String.valueOf(row.getCell(4));
                        String password = String.valueOf(row.getCell(5));
                        String gender = String.valueOf(row.getCell(5));
                        String birthday = String.valueOf(row.getCell(6));
                        String role = String.valueOf(row.getCell(7));
                        List<String> roles = new ArrayList<String>();
                        roles.add(role);
                        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(birthday);
                        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                        UserDTO userDTO = new UserDTO(id, name, email, username, bCryptPasswordEncoder.encode(password),
                                gender, date, roles);
                        userDTOs.add(userDTO);
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if(!userDTOs.isEmpty()){
                    for(UserDTO userDTO : userDTOs){
                    userRepository.save(convertToEntity(userDTO));
                    }
                }
            }
        }
    }

    private Object getValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case ERROR:
                return cell.getErrorCellValue();
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return null;
            case _NONE:
                return null;
            default:
                break;
        }
        return null;
    }

    public static int getNumberOfNonEmptyCells(XSSFSheet sheet, int collumnIndex) {
        int numOfNonEmptyCells = 0;
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);
            if (row != null) {
                XSSFCell cell = row.getCell(collumnIndex);
                if (cell != null) {
                    numOfNonEmptyCells++;
                }
            }
        }
        return numOfNonEmptyCells;
    }
}
