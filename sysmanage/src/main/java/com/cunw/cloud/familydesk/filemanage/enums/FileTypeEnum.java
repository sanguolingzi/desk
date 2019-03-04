package com.cunw.cloud.familydesk.filemanage.enums;

public enum  FileTypeEnum {

    DOC(0),
    DOCX(0),
    PPT(1),
    PPTX(1),
    JPG(2),
    JPEG(2),
    PNG(2),
    AVI(3),
    MP4(3),
    WMV(3),
    RMV(3),
    MPG(3),
    MPEG(3),
    FLV(3),
    UNKOWN(99);

    int fileType;
    FileTypeEnum(int fileType){
        this.fileType = fileType;
    }

    public static int getFileTypeByExten(String fileExten){
        try{
            return FileTypeEnum.valueOf(fileExten.toUpperCase()).fileType;
        }catch (Exception e){

        }
        return UNKOWN.fileType;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }
}
