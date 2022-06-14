function custom_sort(a, b) {
    return new Date(a.TimeOfGPSUpdate).getTime() - new Date(b.TimeOfGPSUpdate).getTime();
}
var your_array = [
    { "AccuracyInMeters" : 16, "CurrentLatitude" : "51570289", "CurrentLongitude" : "477156", "Speed" : 2, "TimeOfGPSUpdate" : "2022-01-24T17:27:40.614Z" },
    { "AccuracyInMeters" : 22, "CurrentLatitude" : "51570352", "CurrentLongitude" : "477293", "Speed" : 1, "TimeOfGPSUpdate" : "2022-01-24T17:21:39.913Z" },
    { "AccuracyInMeters" : 10, "CurrentLatitude" : "51570366", "CurrentLongitude" : "477318", "Speed" : 2, "TimeOfGPSUpdate" : "2022-01-24T17:29:47.248Z" }
];

your_array.sort(custom_sort);

console.log(your_array)
