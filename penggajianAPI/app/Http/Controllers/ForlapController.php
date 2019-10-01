<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use DB;

class ForlapController extends Controller
{
    public function getData(Request $request){

    	$nim=$request->get('nim');
    	$prodi=$request->get('prodi');
    	$data=DB::table($prodi)->where('nim',$nim)->first();

    	return response()->json($data);

    }
}
