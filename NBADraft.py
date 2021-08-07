#!/usr/bin/env python
# coding: utf-8

# In[7]:


import numpy as np
import math
import csv
from tkinter import *
from PIL import ImageTk

class Team:
    
    name = None
    drafting = None
    drafted = None
    
    def __init__(self, name, dicts):
        self.name = name
        self.drafting = Drafting(dicts)
        self.drafted = {}
        
        self.drafted['PG'] = ""
        self.drafted['SG'] = ""
        self.drafted['SF'] = ""
        self.drafted['PF'] = ""
        self.drafted['C'] = ""
        
    def draft_player(self, player, board):
        self.drafting.draft(player, board)
        
                
    def printDraftedPlayers(self):
        print(self.drafting.getDrafted())
        
    def printAvailablePlayers(self):
        self.drafting.getBoard()
        
        
    
               
class Drafting:
    
    draftable = None
    drafted = None
    draftedName = None
    
    #Initializes Drafting
    def __init__(self, dicts):
        
        self.drafted = {}
        self.draftable = dicts
        self.draftedName = {}
        
        self.drafted['PG'] = ""
        self.drafted['SG'] = ""
        self.drafted['SF'] = ""
        self.drafted['PF'] = ""
        self.drafted['C'] = ""
        
        self.draftedName['PG'] = ""
        self.draftedName['SG'] = ""
        self.draftedName['SF'] = ""
        self.draftedName['PF'] = ""
        self.draftedName['C'] = ""
        
    #Drafts a player to a team    
    def draft(self, name, playersDraftable):
        
        player = name
        
        if player in playersDraftable:
            
            playerInfo = playersDraftable.get(name)
            
            position = playerInfo[3]
            
            if(self.drafted.get(position) == ""):
                
                self.drafted[position] = playerInfo
                self.draftedName[position] = playerInfo[0].getName()
                
                self.draftable.pop(name)
                
            
    #Gets the drafted players on a team           
    def getDrafted(self):
        return self.draftedName
    
    #Gets the board of draftable players
    def getBoard(self):
        
        for players in self.draftable.keys():
            
            if(players != None):   
                print(players, "| Offense: ",self.draftable[players][1], "| Defense: ", self.draftable[players][2], "| Position: ", self.draftable[players][3])
                
                
class Players:
    
    playerName = None
    playerPosition = None
    totalOffense = 0
    totalDefense = 0
    
    def __init__(self, name, dicts):
        
        self.playerName = name
        
        self.totalOffense = self.getOffense(name, dicts)
        
        self.totalDefense = self.getDefense(name, dicts)
        
    def getTotalOffense(self):
        return self.totalOffense
    
    def getTotalDefense(self):
        return self.totalDefense
        
    def getPosition(self):
        return self.playerPosition
    
    def getName(self):
        return self.playerName
        
        
    def getOffense(self, name, dicts):
        
        if name in dicts:
                
                players = dicts.get(name)
                
                passing = int(players.get('Passing'))
                driving = int(players.get('Driving'))
                layup = int(players.get('Layup'))
                midrange = int(players.get('Midrange'))
                threePoint = int(players.get('3P'))
                feet, inches = players.get('Height').split(",")
                feet = int(feet)
                inches = int(inches)
                position = players.get('Position')
                
                self.playerPosition = position
                
                offense = passing + driving + layup + midrange + threePoint
                
                if(position == "PG"):
                    
                    if(passing < 80):
                        offense = offense - 5
                        
                    if(threePoint < 82):
                        offense = offense - 2
                        
                    if(midrange < 82):
                        offense = offense - 2
                        
                    if(feet >= 6 and inches >= 5):
                        offense = offense + 3
                        
                    if(feet > 6):
                        offense = offense + 5
                    
                if(position == "SG"):
                    
                    if midrange < 80:
                        offense = offense - 2
                        
                    if threePoint < 75:
                        offense = offense - 1
                        
                    if feet <= 6 and inches < 3:
                        offense = offense - 2
                        
                    if(threePoint > 85 ):
                        offense = offense + 2
                        
                    if(midrange > 87):
                        offense = offense + 2
                    
                    
                if(position == "SF"):
                    
                    if layup < 83:
                        offense = offense - 2
                        
                    if midrange < 77:
                        offense = offense - 2
                        
                    if threePoint < 73:
                        offense = offense - 1
                        
                    if feet >= 6 and inches < 6:
                        offense = offense - 2
                        
                    if(passing > 84):
                        offense = offense + 4
                        
                    if(threePoint > 85):
                        offense = offense + 4
                        
                    if(midrange > 87):
                        offense = offense + 2
                        
                    if feet >= 6 and inches >= 8:
                        offense = offense + 2
                        
                    if feet > 6:
                        offense = offense + 4
                    
                if(position == "PF"):
                    
                    if layup < 85:
                        offense = offense - 2
                        
                    if feet <= 6 and inches < 8:
                        offense = offense - 3
                        
                    if(threePoint > 80):
                        offense = offense + 2
                        
                    if(midrange > 82):
                        offense = offense + 2
                        
                    if feet >= 6 and inches > 11:
                        offense = offense + 1
                        
                    if feet > 6:
                        offense = offense + 3
                    
                if(position == "C"):
                    
                    if layup < 85:
                        offense = offense - 2
                        
                    if feet <= 6 and inches < 10:
                        offense = offense - 3
                        
                    if(threePoint > 76):
                        offense = offense + 2
                                           
                
        return offense
    
    def getDefense(self, name, dicts):
        
        if name in dicts:
            
                players = dicts.get(name)
            
                perimeter = int(players.get('PerimeterD'))
                post = int(players.get('PostD'))
                feet,inches = players.get('Height').split(",")
                feet = int(feet)
                inches = int(inches)
                position = players.get('Position')
                
                defense = perimeter + post
                
                if(position == "PG"):
                    
                    if(perimeter < 75):
                        defense = defense - 2
                        
                    if(feet >= 6 and inches >= 5):
                        defense = defense + 2
                    
                if(position == "SG"):
                    if feet <= 6 and inches <= 3:
                        defense = defense - 2
                    
                    
                if(position == "SF"):
                
                    if feet >= 6 and inches >= 8:
                        defense = defense + 2
                    
                if(position == "PF"):
                    
                    if(post < 83):
                        defense = defense - 3
                    
                    if feet >= 6 and inches >= 11:
                        defense = defense + 2
                    
                if(position == "C"):
                    
                    if(post < 85):
                        defense = defense - 3
                        
                    if feet <= 6 and inches < 10:
                        defense = defense - 3
                        
                    if(post >= 90):
                        defense = defense + 5
                    
                
        return defense


            


singleUserTeam = ""

def load_players(filepath):
    dicts = {}
    #count = 0
    
    with open(filepath, newline = '') as csvfile:
        reader = csv.DictReader(csvfile,delimiter=",")
        
        for row in reader:
            
            tempDictionary = {}
            
            tempDictionary['Name'] = row['Name']
            tempDictionary['Passing'] = row['Passing']
            tempDictionary['Driving'] = row['Driving']
            tempDictionary['Layup'] = row['Layup']
            tempDictionary['Midrange'] = row['Midrange']
            tempDictionary['3P'] = row['3P']
            tempDictionary['PerimeterD'] = row['PerimeterD']
            tempDictionary['PostD'] = row['PostD']
            tempDictionary['Height'] = row['Height']
            tempDictionary['Position'] = row['Position']
            
            dicts[row['Name']] = tempDictionary
        
    return dicts

def create_all_players(dicts):
    
    newDict = {}
    
    for name in dicts.keys():
        NBAPlayer = Players(name,  dicts)
        
        position = NBAPlayer.getPosition()
        
        totalOffense = NBAPlayer.getTotalOffense()
        
        totalDefense = NBAPlayer.getTotalDefense()
        
        newDict[name] = [NBAPlayer, totalOffense, totalDefense, position]
        
    return newDict
        

def playerTeam(team):
    singleUserTeam = team
    

def show(frame):
    frame.tkraise()

def main():
    
    #Setup root
    root = Tk()
    
    root.title("NBA Game")
    
    root.geometry("700x500")
    
    #Import the photos
    bg = PhotoImage(file = "Pics/kobe.png")
    root.bg = bg
    
    #Configure the rows and columns so frame can mold with it
    root.rowconfigure(0,weight=1)
    root.columnconfigure(0,weight=1)
    
    #Set up frames
    startFrame = Frame(root)
    startFrame.grid(row=0, column=0, sticky="nsew")
    
    singleFrame = Frame(root)
    singleFrame.grid(row=0, column=0, sticky="nsew")
    
    multiFrame = Frame(root)
    multiFrame.grid(row=0, column=0, sticky="nsew")
    
    draftFrame = Frame(root)
    draftFrame.grid(row=0, column=0, sticky="nsew")
    
    #Starting Frame ========================================================
    
    # Create Canvas
    canvas = Canvas( startFrame, width = 700,height = 500)
  
    canvas.pack(fill = "both", expand = True)
  
    # Display image
    canvas.create_image( 0, 0, image = bg, anchor = "nw")
    
    # Add Text
    canvas.create_text(570, 75, text = "Welcome To The", font=("Impact", 15),fill="white")
    canvas.create_text(570, 100, text = "NBA DRAFT", font=("Impact", 25),fill="white")
    
    #Adds buttons
    
    #Takes to singleplayer page
    singleplayer = Button(startFrame, text="Singleplayer", command=lambda:show(singleFrame),width=10,font=("Impact", 15), bg="#cccc00")
    singleplayer.pack()
    
    #Takes to multiplayer page
    multiplayer = Button(startFrame, text="Multiplayer",command=lambda:show(multiFrame),width=10,font=("Impact", 15), bg="#cccc00")
    multiplayer.pack()
    
    #Creates windows for buttons
    singleplayer_window = canvas.create_window(627, 200, anchor="ne", window=singleplayer)
    multiplayer_window = canvas.create_window(627, 250, anchor="ne", window=multiplayer)
    
    show(startFrame)
    
    #Singeplayer Frame ===================================================
    
    singleBg = PhotoImage(file = "Pics/Harden.png")
    root.singleBg = singleBg
    
    #Create Canvas
    singleCanvas = Canvas( singleFrame, width = 700,height = 500)
    
    singleCanvas.pack(fill = "both", expand = True)
  
    singleCanvas.create_image( 0, 0, image = singleBg, anchor = "nw")
    
    # Add Text
    singleCanvas.create_text(570, 150, text = "Create a team name:", font=("Impact", 20),fill="white")
    
    #Adds buttons
    
    #Takes to home page
    home = Button(singleFrame, text="Home", command=lambda:show(startFrame),width=10,font=("Impact", 15), bg="#bfbfbf")
    home.pack()
    
    nameEntry = Entry(singleFrame, width=18)
    nameEntry.pack()
    
    singleCanvas.create_window(627,200, anchor="ne", window=nameEntry)
    
    #Start draft button
    startDraft = Button(singleFrame, text="Start Draft", command=lambda:[playerTeam(nameEntry.get()),show(draftFrame)],width=10,font=("Impact", 15), bg="#bfbfbf")
    startDraft.pack()
    
    #Creates windows for buttons
    home_window = singleCanvas.create_window(627, 300, anchor="ne", window=home)
    startDraft = singleCanvas.create_window(627, 250, anchor="ne", window=startDraft)
    
    #Draft Frame==========================================================
    
    draftBg = PhotoImage(file = "Pics/giannis drafted.png")
    root.draftBg = draftBg
    
    #Create Canvas
    draftCanvas = Canvas( draftFrame, width = 700,height = 500)
    
    draftCanvas.pack(fill = "both", expand = True)
  
    draftCanvas.create_image( 0, 0, image = draftBg, anchor = "nw")
    
    # Add Text
    draftCanvas.create_text(160, 60, text = "Draft a player:", font=("Impact", 20),fill="white")
    
    root.mainloop()
    
    
    
    

    


# In[ ]:


main()


# In[6]:


dicts = load_players("players.csv")

allPlayers = create_all_players(dicts)

#     Players("Stephen Curry", dicts)
#     Players("Lebron James", dicts)
#     Players("Kevin Durant", dicts)

#print(allPlayers)

newTeam = Team("Team 1", allPlayers)

newTeam.draft_player("Lebron James", allPlayers)

newTeam.printDraftedPlayers()
newTeam.printAvailablePlayers()

#print(allPlayers)

#Drafting()


# In[ ]:





# In[ ]:




