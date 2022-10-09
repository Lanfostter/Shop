package com.example.shop.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
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
            for (MultipartFile file : files) {
                try {
                    XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
                    XSSFSheet sheet = workbook.getSheetAt(0);
                    Iterator<Row> rows = sheet.iterator();
                    List<UserDTO> userDTOs = new ArrayList<UserDTO>();
                    int rowNumber = 0;
                    while (rows.hasNext()) {
                        Row currentRow = rows.next();
                        // skip header
                        if (rowNumber == 0) {
                            rowNumber++;
                            continue;
                        }
                        Iterator<Cell> cellsInRow = currentRow.iterator();
                        UserDTO userDTO = new UserDTO();
                        int cellIdx = 0;
                        while (cellsInRow.hasNext()) {
                            Cell currentCell = cellsInRow.next();
                            switch (cellIdx) {
                                case 0:
                                    userDTO.setName(currentCell.getStringCellValue());
                                    break;
                                case 1:
                                    userDTO.setEmail(currentCell.getStringCellValue());
                                    break;
                                case 2:
                                    userDTO.setUsername(currentCell.getStringCellValue());
                                    break;
                                case 3:
                                    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                                    userDTO.setPassword(encoder.encode(currentCell.getStringCellValue()));
                                    break;
                                case 4:
                                    userDTO.setGender(currentCell.getStringCellValue());
                                    break;
                                case 5:
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                    try {
                                        userDTO.setBirthday(dateFormat.parse(currentCell.getStringCellValue()));
                                    } catch (ParseException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                    break;
                                case 6:
                                    List<String> roles = new ArrayList<String>();
                                    roles.add(currentCell.getStringCellValue());
                                    userDTO.setRoles(roles);
                                    break;
                                default:
                                    break;
                            }
                            cellIdx++;
                        }
                        userDTOs.add(userDTO);
                    }
                    for (UserDTO userDTO : userDTOs) {
                        if (userRepository.findByUsername(userDTO.getUsername()) == null) {
                            userRepository.save(convertToEntity(userDTO));
                        }
                    }

                    workbook.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }
    }
}
