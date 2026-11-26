const express= require("express");
const app= express();
const PORT= 3000;
const games= [
    {"id":1, "Name":"Among us", "genre":"Strategy"},
    {"id":2, "Name":"Clash Royale", "genre":"Tower Defense"},
    {"id":3, "Name":"Minecraft", "genre":"Adventure"},
    {"id":4, "Name":"Mario kart", "genre":"Race"}
];
app.get("/games",(req,res)=>{
    res.json(games);
})
app.listen(PORT ,()=>{
    console.log("App is running on port" + PORT)
})