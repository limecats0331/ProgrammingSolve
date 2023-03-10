#include <iostream>
#include "vector"

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N;
    int X;
    cin >> N;
    cin >> X;
    vector<int> nums;

    for (int i = 0; i < N; ++i) {
        int tmp;
        cin >> tmp;
        if(tmp < X){
            nums.push_back(tmp);
        }
    }

    for (int i = 0; i < nums.size(); ++i) {
        cout << to_string(nums[i]) << " ";
    }

    return 0;
}