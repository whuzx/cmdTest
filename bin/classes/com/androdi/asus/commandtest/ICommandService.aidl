package com.androdi.asus.commandtest;

interface ICommandService {   
    int doCommand(in String command, out List<String> result);   
}  
