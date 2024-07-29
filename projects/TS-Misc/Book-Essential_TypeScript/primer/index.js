let hatPrice = 100;
console.log(`Hat Price: ${hatPrice}`);
let bootsPrice = "100";
console.log(`Boots Price: ${bootsPrice}`);


if (hatPrice == bootsPrice) {
    console.log("prices = same");
} else {
    console.log("pricess = diff");
}

let totalPrice = hatPrice + bootsPrice;
console.log(`Total Price: ${totalPrice}`);

let aVariable = "var";
console.log(`type: ${typeof aVariable}`);
aVariable = 100;
console.log(`type: ${typeof aVariable}`);
aVariable = Symbol("asdf");
console.log(`type: ${typeof aVariable}`);