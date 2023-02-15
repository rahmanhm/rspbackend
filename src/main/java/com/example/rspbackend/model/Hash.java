package com.example.rspbackend.model;

public class Hash {
    /**
     * 
     * @param txt, text in plain format
     * @param hashType MD5 OR SHA1
     * @return hash in hashType 
     * @credit the following source code is contribution of 
     * @fitorec URL:-> https://stackoverflow.com/posts/25251120/timeline
     * Last Accessed: 14.02.2023 - 1702
     */
    public Hash(){}

    public String getHash(String txt, String hashType) {
        try {
                    java.security.MessageDigest md = java.security.MessageDigest.getInstance(hashType);
                    byte[] array = md.digest(txt.getBytes());
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < array.length; ++i) {
                        sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
                 }
                    return sb.toString();
            } catch (java.security.NoSuchAlgorithmException e) {
                //error action
            }
            return null;
    }

    public  String md5(String txt) {
        return this.getHash(txt, "MD5");
    }

    public  String sha1(String txt) {
        return this.getHash(txt, "SHA1");
    }
}