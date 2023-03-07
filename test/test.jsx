function test() {
  let arr = [1, 2, 3, 4, 5, 6, 7, 8, 9];
  let s = 0;
  arr.map((value) => {
    return (s += value);
  });
}
